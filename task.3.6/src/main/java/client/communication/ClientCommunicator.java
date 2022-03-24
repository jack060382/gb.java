package client.communication;

import java.io.IOException;

public class ClientCommunicator {

    private final ClientConnector connector;

    public ClientCommunicator() {
        connector = new ClientConnector();
    }

    public void sendMessage(String outMess) {
        try {
            connector.getOut().writeUTF(outMess);
        } catch (IOException e) {
            throw new RuntimeException("Error write", e);
        }
    }

    public String receiveMessage() {
        try {
            return connector.getIn().readUTF();
        } catch (IOException e) {
            throw new RuntimeException("Error read", e);
        }
    }

}
