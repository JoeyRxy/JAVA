package mine.learn.multithread;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        TryRunnable app1 = new TryRunnable("thread 1");
        app1.start();

        TryRunnable app2 = new TryRunnable("thread 2");
        app2.start();
    }
}
