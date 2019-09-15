package mine.learn.multithread;

/**
 * InnerTryJoin
 */
class InnerTryJoin extends Thread {

    public InnerTryJoin(String name) {
        super(name);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("子线程" + this.getName() + "开始运行！");
        for (int i = 0; i < 5; i++) {// 瞎几把写点费时的东西即可
            System.out.println("子线程正在运行" + this.getName() + " " + i);
            try {
                sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.getName() + "线程结束");
    }
}

/**
 * TryJoin
 */
public class TryJoin {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "主线程开始");
        InnerTryJoin t1 = new InnerTryJoin("A");
        InnerTryJoin t2 = new InnerTryJoin("B");
        // 不加join，主线程比子线程先结束，且线程之间没有顺序
        // 结果：main主线程开始
        // main主线程结束
        // 子线程B开始运行！
        // current name: B
        // 子线程正在运行B 0
        // 子线程A开始运行！
        // current name: A
        // 子线程正在运行A 0
        // 子线程正在运行B 1
        // t1.start();
        // t2.start();

        // 加join()
        t1.start();
        t2.start();
        // 虽然子线程A,B之间没有什么顺序，但是主线程是在两个都结束之后才结束：即最后一行的sysout()是在两个有join()的子线程结束之后才结束的
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // try {
        // t2.join();
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }

        // 尝试一下 一个子线程有join()，而一个没有的情况
        System.out.println(Thread.currentThread().getName() + "主线程结束");

    }
}