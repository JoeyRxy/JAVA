package mine.learn;

import javax.swing.*;
import java.awt.*;

public class DemoPic extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -8586548698250764909L;

    public DemoPic() {

    }

    public DemoPic(String title) {
        super(title);
        setVisible(true);
        Container container = getContentPane();
        JLabel label = new JLabel("Pic");
        Icon icon = new ImageIcon("src/trump.jpg");
        label.setIcon(icon);
        container.add(label);
    }

    public static void main(String[] args) {
        new DemoPic("Hello world");
    }
}
