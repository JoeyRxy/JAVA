package mine.learn.multithread.complicated;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mine.learn.util.Console;
import mine.learn.util.TaskManager;

/**
 * ImterruptableLongRunningCallable
 * <p>
 * TODO:不明白为什么用Callable Future
 */
public class InterruptableLongRunningCallable extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 5748698408942885011L;
    private JButton b1 = new JButton("Start"), b2 = new JButton("End"), b3 = new JButton("GetRes");
    private TaskManager<String, CallableTask> manager = new TaskManager<>();

    public InterruptableLongRunningCallable() {
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CallableTask task = new CallableTask();
                manager.add(task);
                System.out.println(task + " added to the queue");
            }
        });
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (String res : manager.purge()) {
                    System.out.println(res);
                }
            }
        });
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (TaskItem<String, CallableTask> taskItem : manager) {
                    taskItem.task.id();// No cast required
                }
                for (String res : manager.getRes()) {
                    System.out.println(res);
                }
            }
        });

        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
    }

    public static void main(String[] args) {
        Console.run(new InterruptableLongRunningCallable(), 700, 800);
    }
}