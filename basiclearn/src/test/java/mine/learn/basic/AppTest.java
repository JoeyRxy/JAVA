package mine.learn.basic;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.TreeMap;

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
        assertTrue(true);
    }

    class TStr implements Comparable<TStr> {
        private byte[] b;

        TStr(byte[] b) {
            this.b = b;
        }

        public byte[] getB() {
            return b;
        }

        public void setB(byte[] b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "TStr [b=" + Arrays.toString(b) + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + Arrays.hashCode(b);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TStr other = (TStr) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            return Arrays.equals(b, other.b);
        }

        private AppTest getEnclosingInstance() {
            return AppTest.this;
        }

        @Override
        public int compareTo(TStr o) {
            return -Arrays.compare(b, o.b);
        }

    }

    @Test
    public void testMap() {
        TreeMap<TStr, Integer> map = new TreeMap<>();
        byte[][] bytes = { "bnm".getBytes(), "abc".getBytes(), "def".getBytes(), "kjh".getBytes(), "xyz".getBytes(),
                "yui".getBytes(), "123".getBytes(), "ABC".getBytes() };
        for (byte[] bs : bytes) {
            map.put(new TStr(bs), bs.hashCode());
        }

        System.out.println(map);
        bytes[3][0] = "o".getBytes()[0];
        System.out.println(map);
        Integer integer = map.get(new TStr(bytes[3]));
        System.out.println(integer);
    }
}
