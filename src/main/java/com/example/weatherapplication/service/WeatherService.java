package com.example.weatherapplication.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.weatherapplication.model.weathermodel;
import com.example.weatherapplication.utility.ConfigurationUtility;
import com.example.weatherapplication.utility.OpenStreetMapUtils;

import org.apache.log4j.Logger;

@Service
public class WeatherService {

	@Autowired(required = true)

	private weathermodel we;
	private double lat=0.0;
	private double longg=0.0;
	private String address="";
	

	@Autowired
	private ConfigurationUtility config;
	
	
			public final static Logger log = Logger.getLogger("WeatherService");


	public void setService(weathermodel we) {
		this.we = we;
	}

	public void setlat(double lat) {
		this.lat = lat;
	}

	public void setlongi(double longg) {
		this.longg = longg;
	}

	public double getlat() {
		return lat;
	}

	
public double getlongi() {
	return longg;
	}
	
@Scheduled(fixedRate = 5000)
	public void setdata() {

		if(getlat()!=0.0 && getlongi()!=0.0 ) {
		String url = config.getProperty("weather.api.url");
		String key = config.getProperty("weather.api.key");

		 
		URI URL = null;
		try {
			URL = new URI(url + key + '/' + getlat()  + ',' + getlongi());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RestTemplate restTemplate = new RestTemplate();

		RequestEntity<?> request = RequestEntity.get(URL).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<weathermodel> exchange = restTemplate.exchange(request, weathermodel.class);
		System.out.println("ggggggg");

		weathermodel model = exchange.getBody();
		this.setService(model);
		}
	}

	public weathermodel getCurrentTemp() {

		return this.we;

	}
	
	
	public void setForecastAddress(String add, String url, String key) {
		
		
		if(add  != null) {
			address=add;
		}
	
		
		

		
		Map<String, Double> coords;
	        coords = OpenStreetMapUtils.getInstance().getCoordinates(address);
		        System.out.println("latitude :" + coords.get("lat"));
		        System.out.println("longitude:" + coords.get("lon"));
			    double lat=coords.get("lat");
			    		double longi=coords.get("lon");
			    URI URL = null;
				try {
					URL = new URI(url+key+ '/' + lat + ','+ longi);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				RestTemplate restTemplate = new RestTemplate();

				RequestEntity<?> request = RequestEntity.get(URL)
						.accept(MediaType.APPLICATION_JSON).build();
				ResponseEntity<weathermodel> exchange = restTemplate
						.exchange(request, weathermodel.class);
				
				weathermodel model = exchange.getBody();
				log.info("The time is now {}" + model.getTimezone());


				this.setService(model);
		

	}

	public weathermodel getforcastOnAddress() {
		return this.we;

	}

}
