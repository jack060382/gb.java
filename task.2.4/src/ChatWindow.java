import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow {

    private int EXIT_ON_CLOSE = 99;
    private JFrame chatFrame;

    private JTextField input;
    private JTextArea textArea;

    public ChatWindow() {
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

    private void setBorderLayoutManager(JFrame mainFrame) {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        input = new JTextField("");
        bottomPanel.add(input, BorderLayout.CENTER);

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
        textArea.append(messageStr + "\n");
        input.setText("");
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
