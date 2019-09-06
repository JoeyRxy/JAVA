package mine.learn;

public class City {
    private Integer ID;
    private String Name;
    private String District;
    private String CountryCode;
    private Integer Population;

    @Override
    public String toString() {
        return "City{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", District='" + District + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", Population=" + Population +
                '}';
    }

    public City(Integer ID, String name, String district, String countryCode, Integer population) {
        this.ID = ID;
        Name = name;
        District = district;
        CountryCode = countryCode;
        Population = population;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public Integer getPopulation() {
        return Population;
    }

    public void setPopulation(Integer population) {
        Population = population;
    }

    public City() {
    }

}
