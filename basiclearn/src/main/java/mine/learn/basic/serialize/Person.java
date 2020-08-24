package mine.learn.basic.serialize;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6131408550254380047L;
    private String name;
    private int age;
    private boolean male;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public Person(String name, int age, boolean male) {
        this.name = name;
        this.age = age;
        this.male = male;
    }

    public Person() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, male, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        return age == other.age && male == other.male && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Person [age=" + age + ", male=" + male + ", name=" + name + "]";
    }

}