package mine.learn.annotation4j;

/**
 * Hello world!
 *
 */
@SuppressWarnings("unused")
public class App {
    @Editable() // 要加括号
    App app = new App();

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    @Override
    public String toString() {
        return "App [app=" + app + "]";
    }
}
