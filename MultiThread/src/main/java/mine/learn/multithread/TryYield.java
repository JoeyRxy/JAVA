package mine.learn.multithread;

/**
 * InnerTryYield
 */
class InnerTryYield extends Thread {

    InnerTryYield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(this.getName() + "..." + i);
            if (i == 20) {
                this.yield();
            }
        }
    }
}

/**
 * TryYield
 */
public class TryYield {
    public static void main(String[] args) {
        InnerTryYield t1 = new InnerTryYield("A");
        InnerTryYield t2 = new InnerTryYield("B");

        t1.start();
        t2.start();
    }
}