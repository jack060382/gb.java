package ru.gb.current;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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

    private void communicate() {
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            while (true) {
                String inboundMessage = in.readUTF();

                if (inboundMessage.equals("-exit")) {
                    out.writeUTF("ECHO: Good bye!");
                    out.writeUTF("-end");
                    break;
                }

                out.writeUTF("ECHO: " + inboundMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
