package mine.learn.annotation4j.second;

/**
 * Multiplier
 */
@ExtractInterface("IMultiplier")
public class Multiplier {

    public int multiply(int x, int y) {
        int ans = 0;
        for (int i = 0; i < x; i++) {
            ans = add(ans, y);
        }
        return ans;
    }

    private int add(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        Multiplier t = new Multiplier();
        int ans = t.multiply(11, 16);
        System.out.println(ans);
    }
}