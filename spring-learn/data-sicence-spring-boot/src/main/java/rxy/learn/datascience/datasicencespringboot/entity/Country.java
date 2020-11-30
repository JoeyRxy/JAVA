package rxy.learn.datascience.datasicencespringboot.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

    public static final Country CHINA = new Country("CN", "China", "Asia", 1400000000, "Beijing");

    @Id
    @Column(length = 5)
    private String code;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String continent;

    private long population;

    @Column(length = 50)
    private String capital;

    protected Country() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Country other = (Country) obj;
        return Objects.equals(code, other.code);
    }

    public Country(String code, String name, String continent, int population, String capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.capital = capital;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

}
