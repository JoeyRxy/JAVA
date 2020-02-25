package mine.learn.basic.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A
 */
public class A implements Comparable<A> {

    private int x;

    @Override
    public int compareTo(A o) {
        if (x < o.x)
            return -1;
        else if (x > o.x)
            return 1;
        else
            return 0;
    }

    public A(int x) {
        this.x = x;
    }

    // @Override
    // public int hashCode() {
    // final int prime = 31;
    // int result = 1;
    // result = prime * result + x;
    // return result;
    // }

    // @Override
    // public boolean equals(Object obj) {
    // if (this == obj)
    // return true;
    // if (obj == null)
    // return false;
    // if (getClass() != obj.getClass())
    // return false;
    // A other = (A) obj;
    // if (x != other.x)
    // return false;
    // return true;
    // }

    @Override
    public String toString() {
        return "A [x=" + x + "]";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static void main(String[] args) {
        A a1 = new A(1);
        A a2 = new A(2);
        A a3 = new A(3);

        System.out.println("/** test Map/Set */");
        Map<A, String> map = new HashMap<>();
        map.put(a1, a1.getX() + "");
        map.put(a2, a2.getX() + "");
        if (map.containsKey(new A(1)))
            System.out.println("great!");
        else
            System.err.println("shiit!");
        System.out.println(map);
        map.put(new A(1), "fuck!");
        System.out.println("put等价于change吗？");
        System.out.println(map);
        System.out.println("/** test List */");
        List<A> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        if (list.contains(new A(1)))
            System.out.println("great!");
        else
            System.err.println("shiit!");
        System.out.println("/** test PriorityQueue */");
        PriorityQueue<A> pq = new PriorityQueue<>();
        pq.add(a2);
        pq.add(a3);
        pq.add(a1);

        if (pq.contains(new A(1)))
            System.out.println("great!");
        else
            System.err.println("shiit!");

        System.out.println("/** test sort */");
        A[] array = { a2, a3, a1 };
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
    }

}