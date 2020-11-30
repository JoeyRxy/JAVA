package rxy.learn.datascience.datasicencespringboot.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {

    public static final City SHANG_HAI = new City("Shang Hai", Country.CHINA, "Shang Hai", 40000000);

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 50)
    private String name;

    @OneToOne
    @JoinColumn(referencedColumnName = "code")
    private Country country;

    @Column(length = 50)
    private String district;

    private long population;

    protected City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        City other = (City) obj;
        return id == other.id;
    }

    public City(String name, Country countryCode, String district, long population) {
        this.name = name;
        this.country = countryCode;
        this.district = district;
        this.population = population;
    }

}
