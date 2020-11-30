package rxy.learn.datascience.datasicencespringboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rxy.learn.datascience.datasicencespringboot.entity.City;
import rxy.learn.datascience.datasicencespringboot.entity.Country;
import rxy.learn.datascience.datasicencespringboot.repository.CityRepository;
import rxy.learn.datascience.datasicencespringboot.repository.CountryRepository;

@RestController
public class DataController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @PostMapping("/get_city")
    public List<City> getData() {
        return cityRepository.findAll();
    }

    @PostMapping("/ins_city")
    @ResponseBody
    public boolean insertCity(String name, String country, String district, int population) {
        country = country.toUpperCase();
        Optional<Country> c = countryRepository.findById(country);
        if (c.isEmpty())
            return false;
        cityRepository.save(new City(name, c.get(), district, population));
        return true;
    }

    @PostMapping("/get_country")
    public List<Country> getCountry() {
        return countryRepository.findAll();
    }

    @PostMapping("/ins_country")
    @ResponseBody
    public boolean insertCountry(String code, String name, String continent, int population, String capital) {
        code = code.toUpperCase();
        if (!countryRepository.findById(code).isEmpty())
            return false;
        countryRepository.save(new Country(code, name, continent, population, capital));
        return true;
    }

}
