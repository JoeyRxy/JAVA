package mine.learn.spring.demo4springboot.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "country")
public class Country {

    @Id
    private Integer code;

    private String name;

    private String region;

    private Float surfaceArea;

}
