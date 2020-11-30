package rxy.learn.datascience.datasicencespringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import rxy.learn.datascience.datasicencespringboot.entity.City;
import rxy.learn.datascience.datasicencespringboot.entity.Country;
import rxy.learn.datascience.datasicencespringboot.repository.CityRepository;
import rxy.learn.datascience.datasicencespringboot.repository.CountryRepository;

@Component
public class InitRunner implements ApplicationRunner {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Example<City> shanghai = Example.of(City.SHANG_HAI);
        Example<Country> china = Example.of(Country.CHINA);

        if (!countryRepository.exists(china))
            countryRepository.save(Country.CHINA);

        if (!cityRepository.exists(shanghai))
            cityRepository.save(City.SHANG_HAI);
    }

}
