package mine.learn.spring.demo4springboot.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "city")
public class City {
    @Id
    private Integer id;

    private String name;
}
