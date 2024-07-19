package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.dao.WeatherDao;
import com.project.model.Weather;


@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private WeatherDao weatherDao;

	@Override
	public ArrayList<Weather> findBySearchByLatitudeAndLongitude(double latitude, double longitude) {
		WebClient client = WebClient.builder().baseUrl("https://api.open-meteo.com/v1").build();
		
		ArrayList<Weather> data = new ArrayList<>();
		
		JsonNode json = client.get().uri("/forecast", uriBuilder -> {
			uriBuilder.queryParam("latitude", latitude).
			queryParam("longitude", longitude).
			queryParam("hourly", "temperature_2m").
			queryParam("hourly", "rain").
			queryParam("hourly", "wind_speed_10m").
			queryParam("hourly", "soil_temperature_0cm");
			
			return uriBuilder.build();
		}).retrieve().bodyToMono(JsonNode.class).block();
		
		Weather weather = new Weather();
		
		weather.setLatitude(json.get("latitude").doubleValue());
		weather.setLongitude(json.get("longitude").doubleValue());
		weather.setTemperature(json.get("hourly").get("temperature_2m").asText());
		weather.setRain(json.get("hourly").get("rain").asText());
		weather.setWind(json.get("hourly").get("wind_speed_10m").asText());
		weather.setSoil(json.get("hourly").get("soil_temperature_0cm").asText());
		
		data.add(weather);
		
		return data;
		
	}

	@Override
	public ArrayList<Weather> getWeather(String location) {
		WebClient client = WebClient.create("https://geocoding-api.open-meteo.com/v1");
		
		JsonNode json = client.get().uri("/search", uriBuilder -> {
			uriBuilder.queryParam("name", location);
			
			return uriBuilder.build();
		}).retrieve().bodyToMono(JsonNode.class).block();
		
		System.out.println(json.toPrettyString());
		
		ArrayList<Weather> data = findBySearchByLatitudeAndLongitude(json.get("results").get(0).get("latitude").doubleValue(), json.get("results").get(0).get("longitude").doubleValue());

		for (Weather weather: data)	{
			weather.setLocation(location);
		}
			return data;
	}
	
	
}
	
	
	
	
