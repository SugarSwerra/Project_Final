package com.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather")
public class Weather {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_weather")
	private int id_weather;
	
	@Column(name = "temperature")
	private String temperature;
	
	@Column(name = "rain")
	private String rain;
	
	@Column(name = "wind")
	private String wind;
	
	@Column(name = "soil")
	private String soil;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "location")
	private String location;

	@OneToOne
	@JoinColumn(name = "id_weather", referencedColumnName = "id_city_fk")
	private City city;

	public int getId_weather() {
		return id_weather;
	}

	public void setId_weather(int id_weather) {
		this.id_weather = id_weather;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getRain() {
		return rain;
	}

	public void setRain(String rain) {
		this.rain = rain;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getSoil() {
		return soil;
	}

	public void setSoil(String soil) {
		this.soil = soil;
	}


	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
	
}
