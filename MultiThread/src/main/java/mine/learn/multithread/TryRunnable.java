package mine.learn.multithread;

/**
 * Hello world!
 *
 */
public class TryRunnable implements Runnable {
    private String name;
    private Thread thread;

    public TryRunnable(String name) {
        this.name = name;
        System.out.println("create " + name);
    }

    @Override
    public void run() {
        System.out.println("Running " + name);
        for (int i = 0; i < 4; i++) {
            System.out.println("thread: " + name + ", " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("thread " + name + " interrupted.");

            }
            System.out.println("thread " + name + " exited");
        }
    }

    public void start() {
        System.out.println("start " + name);
        thread = new Thread(this, name);
        thread.start();
    }

    public static void main(String[] args) {
        TryRunnable app1 = new TryRunnable("thread 1");
        app1.start();

        TryRunnable app2 = new TryRunnable("thread 2");
        app2.start();
    }

}
