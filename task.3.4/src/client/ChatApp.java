package client;

import client.communication.ClientCommunicator;
import client.gui.ChatFrame;

import java.util.function.Consumer;


public class ChatApp {

    private final ChatFrame frame;
    private final ClientCommunicator communicator;

    public ChatApp() {
        communicator = new ClientCommunicator();

        Consumer<String> outConsumer = new Consumer<String>() {
            @Override
            public void accept(String outMess) {
                communicator.sendMessage(outMess);
            }
        };

        frame = new ChatFrame(outConsumer);

        new Thread(() -> {
            while (true) {
                String inMess = communicator.receiveMessage();
                frame.getConsumer().accept(inMess);
            }
        }).start();

    }


    private String receiveMassage() {
        return communicator.receiveMessage();
    }

}
