package mine.learn.multithread.join;

public class DownloadThread extends Thread {
    private String name;
    private long sleepTime;

    DownloadThread(String name) {
        super(name);
        this.name = name;
    }

    /**
     * @return the sleepTime
     */
    public long getSleepTime() {
        return sleepTime;
    }

    @Override
    public void run() {
        try {
            // System.out.println("thread-" + name + " downloading...");
            sleepTime = (long) (Math.random() * 5000) + 10000;
            Thread.sleep(sleepTime);
            // System.out.println("thread " + name + " done! duration : " + sleepTime);
            synchronized (Main.lock) {
                Main.lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}