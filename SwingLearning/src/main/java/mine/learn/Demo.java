package mine.learn;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Demo
 */
public class Demo extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -1551688525163299386L;
    JList list;
    JLabel label;
    JButton btn;
    int clicks = 0;

    public Demo() {
        setTitle("动作事件监听示例");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 100, 100);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

    }

}