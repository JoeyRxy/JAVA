package mine.learn.multithread.complicated;

import java.util.concurrent.Callable;

import mine.learn.multithread.Task;

/**
 * CallableTask
 */
public class CallableTask extends Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        run();
        return "Return value of " + this;
    }

}