import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket socket;
    private Socket client;

    public Server() {
        start();
        communicate();
    }

    private void start() {
        try {
            socket = new ServerSocket(8899);
            System.out.println("Socket created...");
            System.out.println("Waiting for connection...");
            client = socket.accept();
            System.out.println("Client connected: ");
            System.out.println(client);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void communicate() {

        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
/*
            int ch;
            while ((ch = in.read()) != -1) {
                System.out.print((char) ch);
            }
*/
            while(true) {
                String inMessage = in.readUTF();

                System.out.println("Message: " + inMessage);
                if (inMessage.equals("-exit")) {
                    out.writeUTF("Good by!");
                    out.writeUTF("-end");
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
