package mine.learn.netprogram.thread0.callback;

import java.util.concurrent.Callable;

/**
 * FindMaxTask
 */
public class FindMaxTask implements Callable<Integer> {

    private int[] data;
    private int start, end;

    @Override
    public Integer call() throws Exception {
        int max = data[start];
        for (int i = start + 1; i < end; i++) {
            if (data[i] > max)
                max = data[i];
        }

        return max;
    }

    public FindMaxTask(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

}