import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationFrame {

    private int EXIT_ON_CLOSE = 99;

    private final JFrame mainFrame;

    public ApplicationFrame() {
        mainFrame = new JFrame();

        mainFrame.setTitle("Application v0.1");
        mainFrame.setBounds(new Rectangle(300, 500));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.setJMenuBar(getJMenuBar());
        //mainFrame.setMenuBar(getMenuBar());

        LayoutManager layoutManager = new BorderLayout();
        mainFrame.setLayout(layoutManager);
        //setFlowLayoutManager(mainFrame);

        JTextArea textArea = new JTextArea();
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");
        textArea.append("fdgdfgdfgdfgsdfgsdfg\n");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    private void setFlowLayoutManager(JFrame mainFrame) {
        LayoutManager layoutManager = new FlowLayout(FlowLayout.LEFT, 0, 0);
        mainFrame.setLayout(layoutManager);

        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btnX = new JButton("X");

        mainFrame.add(btn1);
        mainFrame.add(btn2);
        mainFrame.add(btn3);
        mainFrame.add(btn4);
        mainFrame.add(btnX);
    }

    private void setBoxLayoutManager(JFrame mainFrame) {
        LayoutManager layoutManager = new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS);
        mainFrame.setLayout(layoutManager);

        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btnX = new JButton("X");

        mainFrame.add(btn1);
        mainFrame.add(btn2);
        mainFrame.add(btn3);
        mainFrame.add(btn4);
        mainFrame.add(btnX);
    }

    private void setBorderLayoutManager(JFrame mainFrame) {
        LayoutManager layoutManager = new BorderLayout();
        mainFrame.setLayout(layoutManager);

        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btnX = new JButton("X");

        mainFrame.add(btn1, BorderLayout.NORTH);
        mainFrame.add(btn2, BorderLayout.SOUTH);
        mainFrame.add(btn3, BorderLayout.EAST);
        mainFrame.add(btn4, BorderLayout.WEST);
        mainFrame.add(btnX, BorderLayout.CENTER);
    }

    private JMenuBar getJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        jMenuBar.add(getJMenuFile());
        jMenuBar.add(getJMenuTools());

        return jMenuBar;
    }

    private JMenu getJMenuTools() {
        JMenu jMenu = new JMenu("Tools");

        JMenuItem setAsTpl = new JMenuItem();
        setAsTpl.setText("Set file as template");
        jMenu.add(setAsTpl);

        JMenuItem httpClient = new JMenuItem();
        httpClient.setText("HTTP Client");
        jMenu.add(httpClient);

        return jMenu;
    }

    private JMenu getJMenuFile() {
        JMenu jMenu = new JMenu("File");

        JMenuItem open = new JMenuItem();
        open.setText("Open");
        jMenu.add(open);

        JMenuItem close = new JMenuItem();
        close.setText("Close");
        jMenu.add(close);

        JMenuItem exit = new JMenuItem();
        exit.setText("Exit");
        /*
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(EXIT_ON_CLOSE);
            }
        });
        */
        exit.addActionListener(e -> System.exit(EXIT_ON_CLOSE));
        jMenu.add(exit);

        return jMenu;
    }

    private MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu();
        MenuItem fileMenuItem = new MenuItem();
        fileMenuItem.setLabel("File");
        menu.add(fileMenuItem);
        menuBar.add(menu);

        return menuBar;
    }

}
