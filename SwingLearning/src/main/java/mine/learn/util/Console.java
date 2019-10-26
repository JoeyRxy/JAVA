package mine.learn.util;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Console
 */
public class Console {

    public static void run(JFrame f, int height, int width) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                f.setTitle(f.getClass().getSimpleName());
                f.setSize(height, width);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);
            }
        });
    }
}