package mine.learn;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DemoJlabel extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 6352464225189102630L;

    public DemoJlabel() {
        setBounds(100, 100, 100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = getContentPane();
        setVisible(true);

        JLabel label = new JLabel("Hello Label.");
        label.setText("modify label.");
        container.add(label);
    }

    public static void main(String[] args) {
        // JFrame frame = new JFrame("Demo JLabel");
        // frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // frame.setBounds(100);
        // Container container = frame.getContentPane();
        // frame.setVisible(true);
        // container.add(new DemoJlabel());
        new DemoJlabel();
    }
}
