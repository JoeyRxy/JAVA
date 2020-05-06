package mine.learn.multithread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * MultiThreadPool
 */
public class MultiThreadPool {

    /**
     * MineRunnable
     */
    class MineRunnable implements Runnable {
        private String name;

        MineRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Thread " + name + " start!");
            try {
                Thread.sleep((int) (Math.random() * 2000));
                System.out.println("Thread " + name + " is running!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread has comlete!");
        }

    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(15));
        MultiThreadPool t = new MultiThreadPool();
        for (int i = 0; i < 20; i++) {
            pool.execute(t.new MineRunnable(i + ""));
        }
        pool.shutdown();
    }
}