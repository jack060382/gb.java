package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private ServerSocket socket;
    private Socket client;

    public Server() {
        init();
        communicate();
        System.out.println("Closing the connection...");
        System.out.println("Shutting down...");
        System.out.println("STATUS OK.");
    }

    private void communicate() {
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            new Thread(() -> {
                try {
                    while (true) {
                        System.out.print("Me: ");
                        String outboundMessage = scanner.nextLine();
                        out.writeUTF(outboundMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                String inboundMessage = "";
                try {
                    inboundMessage = in.readUTF();
                } catch (Exception e) {
                    System.out.print("Client disconnected");
                    break;
                }

                if (inboundMessage.equals("-exit")) {
                    out.writeUTF("ECHO: Good bye!");
                    out.writeUTF("-end");
                    break;
                }

                System.out.println("Client: "+inboundMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void init() {
        try {
            socket = new ServerSocket(8899);
            System.out.println("Socket created...");
            System.out.println("Waiting for a connection...");
            client = socket.accept();
            System.out.println("Client connected...");
            System.out.println(client);
            System.out.println("STATUS OK.");
        } catch (IOException e) {
            System.out.println("STATUS NOK.");
            e.printStackTrace();
        }
    }


}
