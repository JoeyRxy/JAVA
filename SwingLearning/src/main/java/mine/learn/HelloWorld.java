package mine.learn;

// import javax.swing.JFrame;
import javax.swing.*;

/**
 * HelloWorld
 */
public class HelloWorld {

    private static void createAndShowGUI() {
        // 整体外观
        JFrame.setDefaultLookAndFeelDecorated(true);
        // 创建窗口及设置
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 添加“Hello World”标签
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        // show
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}