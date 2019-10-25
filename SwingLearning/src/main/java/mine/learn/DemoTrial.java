package mine.learn;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Second
 */
public class DemoTrial extends JDialog {

    public DemoTrial(JFrame frame) {
        super(frame, "Dialog Window's Title", true);
        Container c = getContentPane();
        c.add(new JLabel("a Dialog window"));

        setVisible(true);
        setBounds(100, 100, 100, 100);

    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("父窗体");
        frame.setBounds(50, 50, 300, 300);
        Container c = frame.getContentPane();
        JButton btn = new JButton("弹出对话框");
        c.setLayout(new FlowLayout());
        c.add(btn);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        btn.addActionListener(new ActionListener() {

            // @Override
            public void actionPerformed(ActionEvent e) {
                new DemoTrial(frame);
            }
        });
    }
}