package com.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.model.Weather;
import com.project.service.WeatherService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/weather")
@Component
public class ForecastController {
	@Autowired
	private WeatherService weatherService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByEmail(@QueryParam("location") String location) {
		try {
			ArrayList<Weather> weather = weatherService.getWeather(location);
			
			return Response.status(Response.Status.OK).entity(weather).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
