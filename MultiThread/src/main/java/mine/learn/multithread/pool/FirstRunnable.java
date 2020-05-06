package mine.learn.multithread.pool;

/**
 * FirstRunnable
 */
public class FirstRunnable implements Runnable {
    private final String name;
    private final ThreadCount counter;

    public FirstRunnable(final String name, final ThreadCount counter) {
        this.name = name;
        this.counter = counter;
        counter.inc();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(name + " has done : " + System.currentTimeMillis());
            counter.dec();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

}