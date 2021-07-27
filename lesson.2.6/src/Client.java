package ru.gb.current;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {

    private Socket socket;

    public Client() {
        init();
        communicate();
    }

    private void init() {
        try {
            Thread.sleep(3000);
            socket = new Socket("localhost", 8899);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void communicate() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            AtomicBoolean isAlive = new AtomicBoolean(true);

            new Thread(() -> {
                try {
                    while (true) {
                        String inboundMessage = in.readUTF();
                        if (inboundMessage.equals("-end")) {
                            isAlive.set(false);
                            System.out.println("Please press ENTER to close client...");
                            break;
                        }
                        System.out.println(inboundMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            })
                    .start();

            while (true) {
                if (!isAlive.get()) {
                    System.out.println("Client closing...");
                    System.out.println("STATUS OK.");
                    break;
                }

                System.out.println("Please input message...");
                String outboundMessage = scanner.nextLine();
                out.writeUTF(outboundMessage);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
