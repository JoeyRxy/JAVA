package mine.learn.multithread;

import java.util.ArrayList;
import java.util.List;

/**
 * NotifyAndWait
 * <p>
 * 进程间通讯communication
 */
public class NotifyAndWait {

    private static List<String> list = new ArrayList<String>();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();
    }

    static class ThreadA extends Thread {

        private Object lock;

        public ThreadA(String name, Object lock) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    if (NotifyAndWait.size() != 5) {
                        System.out.println("Thread A wait begin at " + System.currentTimeMillis());
                        lock.wait();
                        System.out.println("Thread A wait end at " + System.currentTimeMillis());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {
        private Object lock;

        public ThreadB(String name, Object lock) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        NotifyAndWait.add();
                        if (NotifyAndWait.size() == 5) {
                            lock.notify();
                            System.out.println("Thread B 已经发出了通知");
                        }
                        System.out.println("Thread B 添加了" + (i + 1) + "个元素!");
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        try {
            Object lock = new Object();

            ThreadA a = new ThreadA("Thread A", lock);
            a.start();

            Thread.sleep(50);

            ThreadB b = new ThreadB("Thread B", lock);
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}