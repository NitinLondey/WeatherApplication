package com.example.weatherapplication.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;


import com.example.weatherapplication.model.weathermodel;
import com.example.weatherapplication.service.WeatherService;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;





@RunWith(MockitoJUnitRunner.class)

public class MainControllerTest {

	@InjectMocks
    WeatherService serivice=new WeatherService();
    
    @InjectMocks
    MainController Controller = new MainController();
    
    @Mock
    weathermodel we;
    
    
    public void setup(){
    }

    @Test
    public void testGetTimezone(){
		String Address = "Florida";


    	weathermodel model = new weathermodel();
    	model.setTimezone("America/New_York");
		model.setLatitude(27.7567667);
		
		model.setLongitude(-81.4639835);
		

		String url="https://api.darksky.net/forecast/" ;
		String key="33483b3854a90f6cea5b8358463c6b1f";
	
		
    	
 		
 		

 		serivice.setForecastAddress(Address,url,key);
 		String modelll= serivice.getforcastOnAddress().getTimezone();
 		
 		assertEquals(modelll,model.getTimezone());

     
 		
 		
 		
 		  }
    
    @Test
    public void testStatusCode() {
    	

    	weathermodel model = new weathermodel();
    	model.setTimezone("America/New_York");
		model.setLatitude(27.7567667);
		
		model.setLongitude(-81.4639835);
		
    	String url="https://api.darksky.net/forecast/" ;
		String key="33483b3854a90f6cea5b8358463c6b1f";
		
		 URI URL = null;
		 
			try {
				URL = new URI(url+key+ '/' + model.getLatitude() + ','+ model.getLongitude());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		RestTemplate restTemplate = new RestTemplate();

		RequestEntity<?> request = RequestEntity.get(URL)
				.accept(MediaType.APPLICATION_JSON).build();
		
		
		ResponseEntity<weathermodel> exchange = restTemplate
				.exchange(request, weathermodel.class);
		
		
    	int hp=exchange.getStatusCodeValue();
    	assertEquals(hp, 200);
    	
    	
    	
    	
    	
    	
    }
    
    
    
    
   

}