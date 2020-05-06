package mine.learn.multithread.pool;

public class ThreadCount {
    private int count;
    private ThreadCountListener listener;

    public ThreadCount() {
        this.count = 0;
    }

    public synchronized void inc() {
        count++;
    }

    public synchronized void dec() {
        count--;
        if (count == 0) {
            listener.todo();
        }
    }

    public void register(ThreadCountListener listener) {
        this.listener = listener;
    }
}
