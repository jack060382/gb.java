package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;


public class ChatFrame implements KeyListener {

    private int EXIT_ON_CLOSE = 99;
    private JFrame chatFrame;

    private JTextField input;
    private JTextArea textArea;
    private final Consumer<String> messageConsumer;
    private final Consumer<String> outConsumer;

    public Consumer<String> getConsumer() {
        return messageConsumer;
    }

    public ChatFrame(Consumer<String> outConsumer) {

        this.outConsumer = outConsumer;

        messageConsumer = new Consumer<String>() {
            @Override
            public void accept(String inMess) {
                textArea.append(inMess+"\n");
            }
        };

        chatFrame = new JFrame();

        chatFrame.setTitle("Chat v0.1");
        chatFrame.setBounds(new Rectangle(800, 500));
        chatFrame.setResizable(false);
        chatFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatFrame.setJMenuBar(getJMenuBar());

        LayoutManager layoutManager = new BorderLayout();
        chatFrame.setLayout(layoutManager);
        setBorderLayoutManager(chatFrame);

        textArea = new JTextArea();

        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chatFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        chatFrame.setVisible(true);
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent arg0) {}

    private void setBorderLayoutManager(JFrame mainFrame) {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        input = new JTextField("-auth u1 pass");
        bottomPanel.add(input, BorderLayout.CENTER);
        input.addKeyListener(this);

        JButton send = new JButton("Send");
        bottomPanel.add(send, BorderLayout.EAST);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!input.getText().trim().isEmpty()) {
                    sendMessage();
                }
            }
        });

    }

    public void sendMessage() {
        String messageStr = input.getText().trim();
        outConsumer.accept(messageStr);
        //textArea.append(messageStr + "\n");
        input.setText("");
        input.grabFocus();
    }

    private JMenuBar getJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(getJMenuFile());
        return jMenuBar;
    }


    private JMenu getJMenuFile() {
        JMenu jMenu = new JMenu("File");

        JMenuItem open = new JMenuItem();
        open.setText("Save chat");
        jMenu.add(open);

        JMenuItem select = new JMenuItem();
        select.setText("Select room");
        jMenu.add(select);

        JMenuItem exit = new JMenuItem();
        exit.setText("Exit");
        exit.addActionListener(e -> System.exit(EXIT_ON_CLOSE));
        jMenu.add(exit);

        return jMenu;
    }


}
