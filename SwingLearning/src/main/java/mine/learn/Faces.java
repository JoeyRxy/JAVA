package mine.learn;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mine.learn.util.Console;

/**
 * Faces
 */
public class Faces extends JFrame {

    private static Icon[] faces;
    private JButton btn1, btn2 = new JButton("Disable");
    private boolean mad = false;

    public Faces() {
        faces = new Icon[] { new ImageIcon(getClass().getResource("trump.jpg")),
                new ImageIcon(getClass().getResource("picture.png")) };
        btn1 = new JButton("Trump", faces[0]);
        setLayout(new FlowLayout());
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (mad) {
                    btn1.setIcon(faces[1]);
                    mad = false;
                } else {
                    btn1.setIcon(faces[0]);
                    mad = true;
                }

                btn1.setVerticalAlignment(JButton.TOP);
                btn1.setHorizontalAlignment(JButton.LEFT);

            }
        });

        btn1.setRolloverEnabled(true);
        btn1.setRolloverIcon(faces[1]);
        btn1.setToolTipText("text");
        add(btn1);

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (btn1.isEnabled()) {
                    btn1.setEnabled(false);
                    btn2.setText("Enabled");
                } else {
                    btn1.setEnabled(true);
                    btn2.setText("Disable");
                }
            }
        });
        add(btn2);
    };

    public static void main(String[] args) {
        Console.run(new Faces(), 700, 800);
    }

}