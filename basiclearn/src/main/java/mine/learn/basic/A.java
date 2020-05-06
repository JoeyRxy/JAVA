package mine.learn.basic;

import java.util.Random;

/**
 * A
 */
public class A {

    public void f(DoSomething doer) {
        Random r = new Random(System.currentTimeMillis());
        doer.dosomething(r);
    }

    public static void main(String[] args) {
        A a = new A();
        a.f(new DoSomething() {

            @Override
            public void dosomething(Object... params) {
                if (params[0] instanceof Random) {
                    Random r = (Random) params[0];
                    System.out.println(r.nextGaussian());
                }
            }
        });
    }
}