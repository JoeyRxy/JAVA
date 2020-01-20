package mine.learn.jspbean;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws Exception
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        boolean check = LogInDAO.check("FksgJA", "+t&R&aGRHw");
        assertTrue(check);
    }
}
