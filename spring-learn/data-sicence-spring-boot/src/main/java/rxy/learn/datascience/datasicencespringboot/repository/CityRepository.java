package rxy.learn.datascience.datasicencespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rxy.learn.datascience.datasicencespringboot.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
