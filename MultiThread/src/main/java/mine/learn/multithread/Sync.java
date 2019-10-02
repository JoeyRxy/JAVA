package mine.learn.multithread;

/**
 * Sync
 */
public class Sync implements Runnable {

    private int a = 0;// IMPORTANT:a是对象的一个成员变量，由于main中只有一个对象供3个线程使用，所以他们使用的是同一个对象（引用）资源

    @Override
    public void run() {
        // synchronized (this) {// 获得当前对象（this）的锁，当然也可以是别的对象
        for (int i = 0; i < 5; i++) {
            a++;
            System.out.println("现在是线程 " + Thread.currentThread().getName() + " 正在作用于a，使a的值变为：" + a);
            try {
                // wait会释放对象this的锁，这里使用的是自动唤醒的对象锁，避免了再构造另一个线程用来唤醒当前线程
                // wait((long) (1000 * Math.random()));
                // sleep是即使睡着了也不会释放锁，即当前对象一直被当前线程占用着，直到它退出
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // }
    }

    public static void main(String[] args) throws InterruptedException {
        Sync obj = new Sync();
        new Thread(obj, "A").start();
        Thread.sleep((long) (Math.random() * 1000));
        new Thread(obj, "B").start();
        Thread.sleep((long) (Math.random() * 1000));
        new Thread(obj, "C").start();
    }

}