package mine.learn;

import java.util.Scanner;

public class TestInput {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println("hi " + string);
        scanner.close();
    }
}
