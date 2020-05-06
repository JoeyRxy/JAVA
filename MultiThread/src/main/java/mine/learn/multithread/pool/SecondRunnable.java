package mine.learn.multithread.pool;

/**
 * SecondRunnable
 */
public class SecondRunnable implements Runnable {
    private String name;

    public SecondRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 5000));
            System.out.println(name + " have done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}