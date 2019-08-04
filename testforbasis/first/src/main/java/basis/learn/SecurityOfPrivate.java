package basis.learn;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

/**
 * SecurityOfPrivate
 */
public class SecurityOfPrivate {

    private List<Integer> list;

    public SecurityOfPrivate() {
        list = new ArrayList<>();
        int k = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            list.add(k++, scanner.nextInt());
        }
    }

    public List<Integer> list() {
        return list;
    }

    public void show() {
        for (Object var : list) {
            System.out.println(var);
        }
    }

    public static void main(String[] args) {
        SecurityOfPrivate t = new SecurityOfPrivate();
        List<Integer> ls = t.list();
        t.show();
        ls.clear();
        t.show();
    }
}