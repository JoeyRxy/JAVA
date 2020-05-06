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

    public App(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public App() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "App [x=" + x + ", y=" + y + "]";
    }
}
