package task8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class Calc extends JFrame {

    private JTextField inputArea;

    private CalcState calcState = new CalcState();

    public Calc() {
        setTitle("Calc v1.0");
        setBounds(100, 100, 300,500);
        setResizable(false);
        setJMenuBar(createMenuBar());

        add(createTopPannel(), BorderLayout.NORTH);
        add(createBottomPannel(),BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createBottomPannel() {
        JPanel bottom = new JPanel();
        GridLayout gridLayout = new GridLayout(5, 3);
        //gridLayout.
        bottom.setLayout(gridLayout);

        calcState.setTextArea(inputArea);

        JButton clear = new JButton(String.valueOf("C"));
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcState.clear();
                inputArea.setText("");
            }
        });
        bottom.add(clear);

        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            DigitBtn digitBtn = new DigitBtn(calcState, String.valueOf(i));
            btn.addActionListener(digitBtn);
            bottom.add(btn);
        }

        JButton dec = new JButton(String.valueOf("."));
        dec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcState.setWaitDecimalPart(true);
            }
        });
        bottom.add(dec);

        JButton negate = new JButton(String.valueOf("+/-"));
        negate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcState.setWaitDecimalPart(true);
            }
        });
        bottom.add(negate);

        JButton plus = new JButton(String.valueOf("="));
        plus.addActionListener(new OperatorBtn(calcState, "="));
        bottom.add(plus);

        JButton res = new JButton(String.valueOf("+"));
        res.addActionListener(new OperatorBtn(calcState, "+"));
        bottom.add(res);

        JButton minus = new JButton(String.valueOf("-"));
        minus.addActionListener(new OperatorBtn(calcState, "-"));
        bottom.add(minus);

        JButton mult = new JButton(String.valueOf("*"));
        mult.addActionListener(new OperatorBtn(calcState, "*"));
        bottom.add(mult);

        JButton div = new JButton(String.valueOf("/"));
        div.addActionListener(new OperatorBtn(calcState, "/"));
        bottom.add(div);

        JButton sqrt = new JButton(String.valueOf("sqrt"));
        sqrt.addActionListener(new OperatorBtn(calcState, "sqrt"));
        bottom.add(sqrt);

        return bottom;
    }

    private JPanel createTopPannel() {
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        inputArea = new JTextField();
        inputArea.setEditable(false);
        top.add(inputArea, BorderLayout.CENTER);

        return top;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setVisible(true);
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);

        JMenuItem editItem = new JMenuItem("Edit");
        fileMenu.add(editItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        return menuBar;
    }

}
