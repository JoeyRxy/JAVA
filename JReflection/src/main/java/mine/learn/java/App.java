package mine.learn.java;

/**
 * Hello world!
 *
 */
public class App {
    private int x;
    private int y;

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public App(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public App() {
    }
}
