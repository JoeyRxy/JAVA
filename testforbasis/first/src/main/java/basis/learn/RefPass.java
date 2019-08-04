package basis.learn;

/**
 * RefPass
 */
public class RefPass {
    private static class A {
        int a;
    }

    public void f(A x) {
        x.a = 10;
    }

    public static void main(String[] args) {
        A t = new A();
        t.a = 100;
        Integer x = 100;
        new RefPass().f(t);
        System.out.println(t.a);
    }
}