package mine.learn.multithread;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;

import mine.learn.util.Console;

/**
 * InterruptableLOngRunningTask
 * FIXME:这个程序有了一些改进，但是当你按下b2时，它会在ExecutorService上调用shutdownNow（），从而会禁用它。如果你试图添加更多的任务，那么就会得到异常。因此，按下b2会使程
 * 序不起作用；我们想要的是在关闭当前任务（并放弃待处理任务）的同时，不会停止任何其他 的事物，在第20章中描述的Java
 * S町的Callable/Future机制正是我们所需要的。我们将定义一个
 */
public class InterruptableLOngRunningTask extends JFrame {

    private static final long serialVersionUID = 6896034354805563507L;
    private JButton b1 = new JButton("Start");
    private JButton b2 = new JButton("End");

    ExecutorService executor = Executors.newSingleThreadExecutor();

    public InterruptableLOngRunningTask() {
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = new Task();
                executor.execute(task);
                System.out.println(task + " added to the queue");
            }
        });
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                executor.shutdown();
            }
        });
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        Console.run(new InterruptableLOngRunningTask(), 700, 800);
    }
}