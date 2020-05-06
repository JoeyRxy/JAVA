package mine.learn.basic.maps;

import java.util.*;

public class Maps implements Comparable<Maps> {

    int[] test;

    public Maps(int[] test) {
        this.test = test;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(test);
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
        Maps other = (Maps) obj;
        return Arrays.equals(test, other.test);
    }

    public int[] getTest() {
        return test;
    }

    public void setTest(int[] test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Maps [test=" + Arrays.toString(test) + "]";
    }

    @Override
    public int compareTo(Maps o) {
        return -Arrays.compare(test, o.test);
    }

    public static void main(String[] args) {
        // HashMap<Maps, String> map = new HashMap<>();
        TreeMap<Maps, Integer> map = new TreeMap<>();
        int arrays[][] = new int[10][];
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = new int[1];
            for (int j = 0; j < arrays[0].length; j++) {
                arrays[i][j] = r.nextInt(100);
            }
        }
        Maps[] maps = new Maps[arrays.length];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new Maps(Arrays.copyOf(arrays[i], arrays[i].length));
            map.put(maps[i], i);
        }
        System.out.println(map);
        System.out.println("====================");
        System.out.println(maps[3]);
        int[] test = maps[3].getTest();
        for (int i = 0; i < test.length; i++) {
            test[i] = 100 + r.nextInt(100);
        }
        System.out.println(maps[3]);
        System.out.println("====================");
        Integer i = map.get(maps[3]);
        System.out.println(i);
        System.out.println("====================");
        System.out.println(map);
    }

}