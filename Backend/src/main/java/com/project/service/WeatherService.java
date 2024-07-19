package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.model.Weather;

public interface WeatherService {
	ArrayList<Weather> findBySearchByLatitudeAndLongitude(double latitude, double longitude);
	
	ArrayList<Weather> getWeather(String location);
}
