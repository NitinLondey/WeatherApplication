package com.example.weatherapplication.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.example.weatherapplication.WeatherapplicationApplication;
import com.example.weatherapplication.model.WeatherAppdeatils;
import com.example.weatherapplication.model.weathermodel;
import com.example.weatherapplication.service.WeatherService;
import com.example.weatherapplication.utility.ConfigurationUtility;

import junit.framework.TestCase;

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
		String key="96531d0644cc8dd9fefc273523d32052";
	
		
    	
 		
 		

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
		String key="96531d0644cc8dd9fefc273523d32052";
		
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