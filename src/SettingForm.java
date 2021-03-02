import java.awt.Container;

// import javax.swing.ButtonGroup;
// import javax.swing.JFrame;
// import javax.swing.JRadioButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SettingForm extends JDialog implements ActionListener {

    private Container c;
    private JPanel panel1;
    private ButtonGroup bgroup1;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;
    private JPanel panel2;
    private ButtonGroup bgroup2;
    private JRadioButton rb4;
    private JRadioButton rb5;
    // private JRadioButton rb6;
    private JButton button1;

    SettingForm() {
        super((JFrame) null, ConnectFour.class.getName(), true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

        panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createTitledBorder("先手"));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        bgroup1 = new ButtonGroup();
        rb1 = new JRadioButton("1P");
        rb2 = new JRadioButton("2P");
        rb3 = new JRadioButton("ランダム");
        bgroup1.add(rb1);
        bgroup1.add(rb2);
        bgroup1.add(rb3);
        panel1.add(rb1);
        rb1.setActionCommand("player:0");
        rb1.addActionListener(this);
        panel1.add(rb2);
        rb2.setActionCommand("player:1");
        rb2.addActionListener(this);
        panel1.add(rb3);
        rb3.setActionCommand("player:-1");
        rb3.addActionListener(this);
        rb1.setSelected(true);
        ConnectFour.player = 0;

        c.add(panel1);

        panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder("人数選択"));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        bgroup2 = new ButtonGroup();
        rb4 = new JRadioButton("1人プレイ");
        rb5 = new JRadioButton("2人プレイ");
        // rb6 = new JRadioButton("ランダム");
        rb4.setSelected(true);
        bgroup2.add(rb4);
        bgroup2.add(rb5);
        // bgroup2.add(rb6);
        panel2.add(rb4);
        rb4.setActionCommand("cpu:1");
        rb4.addActionListener(this);
        panel2.add(rb5);
        rb5.setActionCommand("cpu:0");
        rb5.addActionListener(this);
        // panel2.add(rb6);
        ConnectFour.cpu = 1;

        c.add(panel2);

        button1 = new JButton("開始");
        button1.setActionCommand("start");
        button1.addActionListener(this);
        button1.setAlignmentX(CENTER_ALIGNMENT);
        c.add(button1);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String[] cmd = e.getActionCommand().split(":");
        if (cmd[0].equals("start")) {
            dispose();
        } else if (cmd[0].equals("player")) {
            ConnectFour.player = Integer.parseInt(cmd[1]);
        } else if (cmd[0].equals("cpu")) {
            ConnectFour.cpu = Integer.parseInt(cmd[1]);
        }
    }
}
