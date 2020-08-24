package mine.learn.multithread.join;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static Object lock = new Object();
    private static List<DownloadThread> threads;

    public static void main(String[] args) {
        threads = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            DownloadThread thread = new DownloadThread(i + "");
            threads.add(thread);
        }
        for (var thread : threads) {
            thread.start();
        }
        try {
            synchronized (lock) {
                long start = System.currentTimeMillis();
                while (liveCount() > 0) {
                    lock.wait();
                    System.out.println("Main waiting for " + liveCount() + " thread(s) ...");
                }
                System.out.println("Main duration : " + (System.currentTimeMillis() - start) + " ms");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
        long maxSleepTime = Long.MIN_VALUE;
        for (var thread : threads) {
            long sleepTime = thread.getSleepTime();
            if (sleepTime > maxSleepTime)
                maxSleepTime = sleepTime;
        }
        System.out.println("max op duration is " + maxSleepTime + " ms");
    }

    private static int liveCount() {
        int c = 0;
        for (Thread thread : threads)
            if (thread.isAlive())
                c++;
        if (c == 0)
            synchronized (lock) {
                lock.notify();
            }
        return c;
    }

}