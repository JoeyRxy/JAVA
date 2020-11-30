package rxy.learn.datascience.datasicencespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rxy.learn.datascience.datasicencespringboot.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

}
