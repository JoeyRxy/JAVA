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
                System.out.println(Thread.currentThread().getName() + "开始运行！");
                Thread.sleep((int) (Math.random() * 5000));
                System.out.println("线程" + Thread.currentThread().getName() + "想要获得锁……" + System.currentTimeMillis());
                synchronized (lock) {
                    System.out.println("线程" + Thread.currentThread().getName() + "获得了锁！" + System.currentTimeMillis());
                    if (NotifyAndWait.size() != 10) {
                        System.out.println(
                                "线程" + Thread.currentThread().getName() + "释放了锁：" + System.currentTimeMillis());
                        lock.wait();
                        System.out.println(
                                "线程" + Thread.currentThread().getName() + "获得了锁： " + System.currentTimeMillis());
                    }
                    Thread.sleep((int) (Math.random() * 5000));
                    System.out.println(
                            "线程" + Thread.currentThread().getName() + "执行完了同步代码块：" + System.currentTimeMillis());
                    lock.notifyAll();
                    System.out.println(
                            "线程" + Thread.currentThread().getName() + "已经通知其他线程进入锁池：" + System.currentTimeMillis());
                }
                System.out.println(Thread.currentThread().getName() + "开始执行 非 同步任务……");
                Thread.sleep(5000);
                System.out.println("线程" + Thread.currentThread().getName() + "执行完了非同步任务！");
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
                System.out.println(Thread.currentThread().getName() + "开始运行！");
                Thread.sleep((int) (Math.random() * 5000));
                System.out.println("线程" + Thread.currentThread().getName() + "想要获得锁……" + System.currentTimeMillis());
                synchronized (lock) {
                    System.out.println("线程" + Thread.currentThread().getName() + "获得了锁！" + System.currentTimeMillis());
                    for (int i = 0; i < 10; i++) {
                        NotifyAndWait.add();
                        if (NotifyAndWait.size() == 5) {
                            lock.notifyAll();
                            System.out.println("线程" + Thread.currentThread().getName() + "已经通知其他线程进入锁池："
                                    + System.currentTimeMillis());
                            System.out.println(
                                    "线程" + Thread.currentThread().getName() + "释放了锁：" + System.currentTimeMillis());
                            lock.wait();
                        }
                        System.out.println("线程" + Thread.currentThread().getName() + " 添加了" + (i + 1) + "个元素!");
                        Thread.sleep((int) (Math.random() * 5000));
                        if (NotifyAndWait.size() == 13) {
                            lock.notifyAll();
                            System.out.println("线程" + Thread.currentThread().getName() + "已经通知其他线程进入锁池："
                                    + System.currentTimeMillis());
                            System.out.println(
                                    "线程" + Thread.currentThread().getName() + "释放了锁：" + System.currentTimeMillis());
                            lock.wait();
                        }
                    }
                    lock.notifyAll();
                }
                System.out.println(Thread.currentThread().getName() + "开始执行 非 同步任务……");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        try {
            Object lock = new Object();

            ThreadA a = new ThreadA(" A", lock);
            a.start();

            Thread.sleep(50);

            ThreadB b = new ThreadB(" B", lock);
            ThreadB c = new ThreadB(" C", lock);
            b.start();
            c.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}