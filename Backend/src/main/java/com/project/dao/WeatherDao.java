package com.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.model.City;

public interface WeatherDao extends CrudRepository<City, Integer> {

}
