package basis.learn;

import java.util.List;

/**
 * SecurityOfPrivateTest
 */
public class SecurityOfPrivateTest {

    public static void main(String[] args) {
        SecurityOfPrivate t = new SecurityOfPrivate();
        List<Integer> ls = t.list();
        ls.set(1, -1);//ATTENTION:由于是传引用，故可以通过t.list()返回私有数据成员的引用，进而修改私有数据！
        t.show();
    }
}