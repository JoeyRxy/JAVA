package mine.learn.multithread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Main
 */
public class Main {

    public void first() throws InterruptedException {
        ThreadPoolExecutor exeFirst = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(7));
        ThreadCount counter = new ThreadCount();
        counter.register(() -> {
            try {
                System.out.println("first all complete!" + System.currentTimeMillis());
                second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 12; i++)
            exeFirst.execute(new FirstRunnable("线程First" + i, counter));
        exeFirst.shutdown();
    }

    public void second() throws InterruptedException {
        ThreadPoolExecutor exeSecond = new ThreadPoolExecutor(5, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(7));
        for (int i = 0; i < 12; i++)
            exeSecond.execute(new SecondRunnable("线程Second" + i));

        exeSecond.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        Main t = new Main();
        t.first();
    }
}