import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    private Socket socket;

    public Client() {
        start();
        communicate();
    }

    private void start() {
        try {
            Thread.sleep(3000);
            socket = new Socket("localhost", 8899);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void communicate() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            while(true) {
                String inMessage = in.readUTF();
                if (inMessage.equals("-exit")) {
                    break;
                }
                else {
                    System.out.println(inMessage);
                }

                String message = scanner.nextLine();
                out.writeUTF(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
