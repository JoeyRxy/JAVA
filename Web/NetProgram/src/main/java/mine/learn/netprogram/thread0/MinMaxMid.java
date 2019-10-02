package mine.learn.netprogram.thread0;

import java.util.Arrays;

/**
 * MinMaxMid
 * <p>
 * {@code thread.join()}让 当前线程（成为joined）阻塞，等待插队（join）线程{@code thread}结束
 */
public class MinMaxMid {

    public static void main(String[] args) throws InterruptedException {
        int N = 10000;
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = Math.random() * i;
        }

        SortThread t = new SortThread(a);
        t.start();

        t.join();// 等待排完序再找结果
        System.out.println("Min: " + a[0]);
        System.out.println("Max: " + a[N - 1]);
        System.out.println("Mid: " + a[N / 2]);

    }
}

class SortThread extends Thread {
    double[] a;

    SortThread(double[] a) {
        this.a = a;
    }

    @Override
    public void run() {
        Arrays.sort(a);
    }
}