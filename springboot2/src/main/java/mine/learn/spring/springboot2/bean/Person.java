package mine.learn.spring.springboot2.bean;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Person
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;
    private Boolean boss;
    private Integer age;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

    public Person(String lastName, boolean boss, int age, Map<String, Object> maps, List<Object> lists, Dog dog) {
        this.lastName = lastName;
        this.boss = boss;
        this.age = age;
        this.maps = maps;
        this.lists = lists;
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person [age=" + age + ", boss=" + boss + ", dog=" + dog + ", lastName=" + lastName + ", lists=" + lists
                + ", maps=" + maps + "]";
    }

}