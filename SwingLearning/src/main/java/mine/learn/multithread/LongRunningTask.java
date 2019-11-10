package mine.learn.multithread;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mine.learn.util.Console;

/**
 * LongRunningTask
 * <p>
 * FIXME:当按下bl时，事件分发线程会突然被占用去执行这个长期运行的任务。此时你会看到，这
 * 个按钮甚至不会马上弹起来，因为正常情况下会重绘屏幕的事件分发钱程现在处于t状态。并 且你也不能做其他任何事，例如按下b2 ，
 * 因为这个程序在M的任务完成，从而使得事件分发线 程再次可用之前，是不会做出响应的。
 */
public class LongRunningTask extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 6896034354805563507L;
    private JButton b1 = new JButton("Start");
    private JButton b2 = new JButton("End");

    public LongRunningTask() {
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    System.out.println("Task Interrupt");
                    e1.printStackTrace();
                }
                System.out.println("Task Completed");
            }
        });

        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Thread.currentThread().interrupt();
            }
        });

        setLayout(new FlowLayout());
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        Console.run(new LongRunningTask(), 700, 800);
    }
}