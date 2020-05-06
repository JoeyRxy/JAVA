package mine.learn.multithread.pool;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * MinePoolExecutor
 */
public class MinePoolExecutor {
    ThreadCount counter;

    public MinePoolExecutor(List<Runnable> targetList) {
        int size = targetList.size();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(size, size << 1, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(size << 1));
        for (Runnable target : targetList) {
            executor.execute(target);
        }
    }

    public void afterDone(CallBack callBack, Object... params) {
        callBack.f(params);
    }

}