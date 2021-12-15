package server;

import java.io.*;
import java.net.Socket;
import java.sql.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ClientHandler {

    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private AuthService.Entry user;
    private String privateRegExp = "private\\[([a-z0-9\\;]+)\\]:\\s(.*)";
    private final String LogDir = "logs";
    private int LastMessagesForLoad = 3;
    private final int AUTH_TIMEOUT = 300;
    private final Socket socket;
    private DataOutputStream logOut;
    private final int logBufferReadSize = 100;
    private final int lastLogMessagesForLoad = 10;

    public ClientHandler(Server server, Socket socket) {
        this.socket = socket;
        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            server.getService().execute(() -> {
                try {
                    doAuthentication();
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            });
            /*
            new Thread(() -> {
                try {
                    doAuthentication();
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
            */
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }

    private void startLogFile() throws IOException, FileNotFoundException {
        File logDir = new File(LogDir);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        File file = new File(logDir, user.getLogin()+".txt");

        if (!file.exists()) {
            file.createNewFile();
        }
        //this.logOut = new BufferedWriter(new FileWriter(file));
        this.logOut = new DataOutputStream(new FileOutputStream(file, true));
    }

    private void loadLastLocalMessages() {
        File logDir = new File(LogDir);
        File file = new File(logDir, user.getLogin()+".txt");
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            boolean readLog = true;
            long position = file.length();
            byte buffer[] = new byte[logBufferReadSize];
            String readData = "";
            String[] messagesForLoad = null;
            do {
                position -= buffer.length;
                if (position < 0) {
                    raf.seek(0);
                    byte bufferFirst[] = new byte[logBufferReadSize - (int)Math.abs(position)];
                    raf.read(bufferFirst);
                    readData = new String(bufferFirst, "UTF-8") + readData;
                }
                else {
                    raf.seek(position);
                    raf.read(buffer);
                    readData = new String(buffer, "UTF-8") + readData;
                }
                messagesForLoad = readData.split("[\n]");

                if (position < 1 || messagesForLoad.length > lastLogMessagesForLoad) {
                    readLog = false;
                }
            } while (readLog);
            int start = messagesForLoad.length - lastLogMessagesForLoad;
            if (start < 0) {
                start = 0;
            }
            for (int i = start; i < messagesForLoad.length ; i++) {
                sendMessage(messagesForLoad[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeLog(String msg) {
        try {
            logOut.writeUTF(msg + "\n");
        } catch (IOException e) {
            // skip
        }

        //logOut.write(msg + "\n");
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(String.format("User[%s] is out.", user.getNick()));

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return user.getNick();
    }

    private void startTimeOutCounter() {
        long incomeTime = System.currentTimeMillis();
        new Thread(() -> {
                while (true) {
                    if (Math.floor((System.currentTimeMillis() - incomeTime)/1000) >= AUTH_TIMEOUT) {
                        sendMessage("Auth timeout! Connection closed.");
                        closeConnection();
                        break;
                    }
                }
        }).start();
    }

    private void changeNick(String credential) {
        String[] credentials = credential.split("\\s");
        if (credentials.length == 2) {
            Optional<AuthService.Entry> maybeExistUser = server.getAuthService()
                    .findUserByNick(credentials[1]);

            if (!maybeExistUser.isPresent()) {
                user.setNick(credentials[1]);
                server.getAuthService().updateUser(user);
                sendMessage("Nickname changed successful on "+credentials[1]+"!");
            }
            else {
                sendMessage("This nickname is already use!");
            }
        }
        else {
            sendMessage("Incorrect nickname change command!");
        }
    }

    private void doAuthentication() throws IOException {
        sendMessage("Glad to see you!");
        sendMessage("Please auth in "+AUTH_TIMEOUT+" seconds. -auth [login] [pass]");
        sendMessage("For change nick use. -changenick [newnick]");
        sendMessage("For private message use. private[nick1;...]: message");
        sendMessage("For exit use. -exit");
        startTimeOutCounter();
        while (true) {
            String maybeCredentials = in.readUTF();
            /** sample: -auth login1 password1 */
            if (maybeCredentials.startsWith("-auth")) {
                String[] credentials = maybeCredentials.split("\\s");
                Optional<AuthService.Entry> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);

                if (maybeUser.isPresent()) {
                    AuthService.Entry user = maybeUser.get();
                    if (server.isNotUserOccupied(user.getNick())) {
                        this.user = user;
                        sendMessage("AUTH OK.");
                        sendMessage("Welcome.");
                        server.broadcastMessage(String.format("User[%s] entered chat.", user.getNick()));
                        server.subscribe(this);
                        try {
                            startLogFile();
                            loadLastLocalMessages();
                        } catch (Exception e) {
                            sendMessage("System error, please call administrator.");
                            System.exit(0);
                        }
                        return;
                    } else {
                        sendMessage("Current user is already logged in");
                    }
                } else {
                    sendMessage("Invalid credentials.");
                }
            } else {
                sendMessage("Invalid auth operation");
            }
        }
    }

    public void sendMessage(String outboundMessage) {
        sendMessage(outboundMessage, false);
    }

    public void sendMessage(String outboundMessage, boolean logging) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (logging) {
            writeLog(outboundMessage);
        }
    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();

            if (inboundMessage.equals("-exit")) {
                break;
            }

            if (inboundMessage.startsWith("-changenick")) {
                changeNick(inboundMessage);
                continue;
            }

            String[] receivers = getPrivateReceivers(inboundMessage);
            String msg = "["+user.getNick()+"]: " + inboundMessage;
            if (receivers.length > 0) {
                server.privateMessage(receivers, msg);
            }
            else {
                server.broadcastMessage(msg);
            }
            writeLog(msg);
        }
    }

    private String[] getPrivateReceivers(String inboundMessage) {
        if (inboundMessage.matches(privateRegExp)) {
            System.out.println("match");
            Pattern p = Pattern.compile(privateRegExp);
            Matcher m = p.matcher(inboundMessage);
            m.find();
            String receiversString = m.group(1);
            receiversString += ";" + user.getNick();
            return receiversString.split(";");
        }
        String[] receivers = {};
        return receivers;
    }



}
