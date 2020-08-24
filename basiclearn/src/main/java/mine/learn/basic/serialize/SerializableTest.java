package mine.learn.basic.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        deserialize();
    }

    public static void serialize() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("object.bin")));
        Industry industry1 = new Industry("Audi", null);
        Person p1 = new Person("zs", 10, true);
        Person p2 = new Person("ls", 20, false);
        Person p3 = new Person("ww", 30, true);
        industry1.addEmployee(p1);
        industry1.addEmployee(p2);
        industry1.addEmployee(p3);
        oos.writeObject(industry1);
        oos.close();
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("object.bin")));
        Object obj = ois.readObject();
        if (obj instanceof Industry) {
            Industry industry = (Industry) obj;
            System.out.println(industry);
            int hashCode = industry.hashCode();
            System.out.println(hashCode);
            Industry industry2 = new Industry(industry.getName(), industry.getEmployee());
            boolean equals = industry.equals(industry2);
            System.out.println(equals);
        } else {
            System.err.println("FUCKED!");
        }
        ois.close();
    }
}