package mine.learn.basic;

import java.util.*;

/**
 * PriorityQueueTest
 */
public class PriorityQueueTest {

    /**
     * Node
     */
    public class Node implements Comparable<Node> {

        int key;
        String attr;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + Objects.hash(key);
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
            Node other = (Node) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            return key == other.key;
        }

        private PriorityQueueTest getEnclosingInstance() {
            return PriorityQueueTest.this;
        }

        @Override
        public int compareTo(Node o) {
            return attr.compareTo(o.attr);
        }

        public Node(int key, String attr) {
            this.key = key;
            this.attr = attr;
        }

        @Override
        public String toString() {
            return String.format("%-5d: %-8s", key, attr);
        }
    }

    private PriorityQueue<Node> pq;
    private Map<Integer, Node> map;

    public PriorityQueueTest() {
        pq = new PriorityQueue<>(10);
        map = new HashMap<>();
    }

    public void add(int key, String val) {
        Node node = map.get(key);
        if (node != null) {// java 自带的优先队列具有非常大的弊端！remove操作耗时O(n)，即使使用一个Map记录了各个端点的位置也不行！
            pq.remove(node);
        }
        pq.add(new Node(key, val));
    }

    public void remove(int key) {
        pq.remove(new Node(key, null));
    }

    public static void main(String[] args) {
        PriorityQueueTest t = new PriorityQueueTest();
        t.add(10, "val");
        t.add(4, "hello");
        t.add(1, "world");
        t.add(7, "abd");
        t.add(9, "tech");
        t.remove(4);
        t.add(4, "baby");
    }
}