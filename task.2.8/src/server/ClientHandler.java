package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    private String name;
    private String privateRegExp = "private\\[([a-z0-9\\;]+)\\]:\\s(.*)";
    private final int AUTH_TIMEOUT = 60;
    private final Socket socket;

    public ClientHandler(Server server, Socket socket) {
        this.socket = socket;
        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    doAuthentication();
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            })
                    .start();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(String.format("User[%s] is out.", name));

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

    public String getName() {
        return name;
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

    private void doAuthentication() throws IOException {
        sendMessage("Glad to see you!");
        sendMessage("Please auth in "+AUTH_TIMEOUT+" seconds. -auth [login] [pass]");
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
                    if (server.isNotUserOccupied(user.getName())) {
                        name = user.getName();
                        sendMessage("AUTH OK.");
                        sendMessage("Welcome.");
                        server.broadcastMessage(String.format("User[%s] entered chat.", name));
                        server.subscribe(this);
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
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();
            if (inboundMessage.equals("-exit")) {
                break;
            }

            String[] receivers = getPrivateReceivers(inboundMessage);

            if (receivers.length > 0) {
                server.privateMessage(receivers, "["+name+"]: " + inboundMessage);
            }
            else {
                server.broadcastMessage("["+name+"]: " + inboundMessage);
            }

        }
    }

    private String[] getPrivateReceivers(String inboundMessage) {
        if (inboundMessage.matches(privateRegExp)) {
            Pattern p = Pattern.compile(privateRegExp);
            Matcher m = p.matcher(inboundMessage);
            m.find();
            String receiversString = m.group(1);
            receiversString += ";" + name;
            return receiversString.split(";");
        }
        String[] receivers = {};
        return receivers;
    }



}
