package mine.learn.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * TryCallable
 */
public class TryCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i;
        for (i = 0; i < 15; i++) {
            String thread_name = Thread.currentThread().getName();
            System.out.println(thread_name + "\t" + i);
        }
        return i;
    }

    public static void main(String[] args) {
        TryCallable t = new TryCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(t);
        for (int i = 0; i < 25; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量的值为" + i);
            if (i == 20) {
                new Thread(futureTask, "有返回值的线程 ").start();
            }
        }
        try {
            Integer j = futureTask.get();
            System.out.println("子线程的返回值：" + j);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}