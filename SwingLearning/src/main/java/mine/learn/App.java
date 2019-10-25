package mine.learn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 2445145079770774478L;

    public App(String string) {
        super(string);
    }

    public static void main(String[] args) {
        App app = new App("Hello World");
        JLabel label = new JLabel("JLabel");
        app.add(label);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // app.setSize(300, 300);
        app.setBounds(100, 200, 300, 400);
        app.setVisible(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                label.setText("label changed");
            }
        });

        JButton btn1 = new JButton("Try Button");
        app.add(btn1);
        btn1.setBounds(130, 170, 80, 20);
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = ((JButton) e.getSource()).getText();
                label.setText(text);
            }
        });
    }
}
