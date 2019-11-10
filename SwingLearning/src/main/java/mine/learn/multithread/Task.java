package mine.learn.multithread;

/**
 * Task
 */
public class Task implements Runnable {

    private static int counter = 0;
    private final long id = counter++;

    @Override
    public void run() {
        System.out.println(this + " started");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
            e.printStackTrace();
            return;
        }
        System.out.println(this + " completed");
    }

    @Override
    public String toString() {
        return "Task " + id;
    }

    public long id() {
        return id;
    }

}