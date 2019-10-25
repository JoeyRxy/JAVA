package mine.learn;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * EventHandling
 */
public class EventHandling extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -6127477967324310415L;
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public EventHandling() {
        mainFrame = new JFrame("监听事件处理");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("header", JLabel.CENTER);
        statusLabel = new JLabel("status", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(statusLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {
        headerLabel.setText("控制按钮");

        JButton okbtn = new JButton("OK");
        JButton submitbtn = new JButton("submit");
        JButton cancelbtn = new JButton("cancel");

        okbtn.setActionCommand("OK");
        submitbtn.setActionCommand("submit");
        cancelbtn.setActionCommand("cancel");

        okbtn.addActionListener(new ButtonClickListener());
        submitbtn.addActionListener(new ButtonClickListener());
        cancelbtn.addActionListener(new ButtonClickListener());

        controlPanel.add(okbtn);
        controlPanel.add(submitbtn);
        controlPanel.add(cancelbtn);

        mainFrame.setVisible(true);
    }

    // 我觉得内部是用thread实现的，不用的时候挂起，使用的时候run
    private class ButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals("OK")) {
                statusLabel.setText("点击了OK按钮");
            } else if (cmd.equals("submit")) {
                statusLabel.setText("点击了submit按钮");
            } else {
                statusLabel.setText("点击了cancel按钮");
            }
        }

    }

    public static void main(String[] args) {
        EventHandling t = new EventHandling();
        t.showEventDemo();
    }
}