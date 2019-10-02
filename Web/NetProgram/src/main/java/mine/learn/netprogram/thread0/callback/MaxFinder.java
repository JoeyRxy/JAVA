package mine.learn.netprogram.thread0.callback;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * MaxFinder
 */
public class MaxFinder {

    public static int max(int[] data) throws InterruptedException, ExecutionException {
        int len = data.length;
        if (len == 1)
            return data[0];

        FindMaxTask task1 = new FindMaxTask(data, 0, len / 2);
        FindMaxTask task2 = new FindMaxTask(data, len / 2, len);

        // 创建线程
        ExecutorService service = Executors.newFixedThreadPool(2);// 两个线程

        // future得到结果
        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);

        return Math.max(future1.get(), future2.get());
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Random r = new Random(System.currentTimeMillis());
        int N = 500000;
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = r.nextInt();
        }

        long start, end, duration;
        int max_;
        start = System.currentTimeMillis();
        max_ = MaxFinder.max(data);
        end = System.currentTimeMillis();
        duration = end - start;

        System.out.println("MultiThread duration : " + duration + "\nres: " + max_);

        start = System.currentTimeMillis();
        max_ = data[0];
        for (int i = 1; i < N; i++) {
            if (max_ < data[i])
                max_ = data[i];
        }
        end = System.currentTimeMillis();
        duration = end - start;

        System.out.println("SingleThread duration : " + duration + "\nres: " + max_);
    }
}