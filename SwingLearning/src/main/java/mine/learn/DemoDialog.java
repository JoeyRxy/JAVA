package mine.learn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoDialog extends JDialog {
    public DemoDialog(Frame frame) {
        super(frame,true);
        Container c = getContentPane();
        c.add(new JLabel("This is a Dialog"));

        setVisible(true);
        setBounds(100, 100, 100, 100);
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("P Window");
        frame.setBounds(50, 50, 300, 300);
        Container c = frame.getContentPane();
        JButton btn = new JButton("Pop up Dialog Window");
        c.add(btn);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DemoDialog(frame);
            }
        });
    }
}
