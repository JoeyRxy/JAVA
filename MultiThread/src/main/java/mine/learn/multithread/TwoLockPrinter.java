package mine.learn.multithread;

/**
 * 基于两个lock实现连续打印abcabc....
 * 
 * @author lixiaoxi
 *
 */
public class TwoLockPrinter implements Runnable {

    // 打印次数
    private static final int PRINT_COUNT = 10;
    // 前一个线程的打印锁
    private final Object prevLock;
    // 本线程的打印锁
    private final Object thisLock;
    // 打印字符
    private final char printChar;

    public TwoLockPrinter(Object fontLock, Object thisLock, char printChar) {
        this.prevLock = fontLock;
        this.thisLock = thisLock;
        this.printChar = printChar;
    }

    @Override
    public void run() {
        // 连续打印PRINT_COUNT次
        for (int i = 0; i < PRINT_COUNT; i++) {
            // 获取前一个线程的打印锁
            synchronized (prevLock) {
                // 获取本线程的打印锁
                synchronized (thisLock) {
                    // 打印字符
                    System.out.print(printChar);
                    // 通过本线程的打印锁唤醒后面的线程
                    // notify和notifyall均可,因为同一时刻只有一个线程在等待
                    thisLock.notify();// 谁在wait这个锁？以这个对象为prevLock的线程，比如当前线程为“打印A的线程”，那么，thisLock就是a，那么“打印B的线程”正在等待得到这个锁；这是在运行起来之后，那么初始化的时候呢？一开始“打印B的线程”还没有运行啊？可能notify这个函数在没有可唤醒的进程的时候，就当什么都没干吧
                }
                // 不是最后一次则通过prevLock等待被唤醒
                // 必须要加判断，不然虽然能够打印10次，但10次后就会直接死锁
                if (i < PRINT_COUNT - 1) {
                    try {
                        // 通过prevLock等待被唤醒；当前进程终止，并放弃prevLock的锁
                        prevLock.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 打印A线程的锁
        Object lockA = new Object();
        // 打印B线程的锁
        Object lockB = new Object();
        // 打印C线程的锁
        Object lockC = new Object();

        // 打印a的线程
        Thread threadA = new Thread(new TwoLockPrinter(lockC, lockA, 'A'));
        // 打印b的线程
        Thread threadB = new Thread(new TwoLockPrinter(lockA, lockB, 'B'));
        // 打印c的线程
        Thread threadC = new Thread(new TwoLockPrinter(lockB, lockC, 'C'));

        // 依次开启a b c线程
        threadA.start();
        Thread.sleep(10); // 确保按顺序A、B、C执行
        threadB.start();
        Thread.sleep(10);
        threadC.start();
        Thread.sleep(10);
    }

}