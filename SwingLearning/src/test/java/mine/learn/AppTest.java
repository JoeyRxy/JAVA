package mine.learn;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        Class<Person> clazz = Person.class;
        System.out.println(clazz);

        Person p = new Person(5545, "Jesus");
        Class<? extends AppTest> class1 = getClass();
        System.out.println(class1);

        Class<? extends Person> class2 = p.getClass();
        System.out.println(class2);

        Constructor<Person> clazzCtor = clazz.getConstructor(int.class, String.class);
        Constructor<? extends Person> class1Ctor = class2.getConstructor(int.class, String.class);

        Person p1 = clazzCtor.newInstance(23425, "MotherFucker");
        Person p2 = class1Ctor.newInstance(9876, "BadAss");

        System.out.println(p1);
        System.out.println(p2);
    }
}

class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return ("id:" + id + "\tname:" + name);
    }

}
