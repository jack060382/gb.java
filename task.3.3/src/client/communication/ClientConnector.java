package client.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnector {
    private final DataInputStream in;
    private final DataOutputStream out;
    private final Socket socket;

    public ClientConnector() {
        try {
            socket = new Socket("localhost", 8888);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Chat start error", e);
        }
    }

    public DataInputStream getIn() {
        return in;
    }

    public DataOutputStream getOut() {
        return out;
    }
}
