package com.example.weatherapplication.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigurationUtility {

	

	    @Autowired
	    private Environment environment;

	    public String getProperty(String propertyKey) {
	        return environment.getProperty(propertyKey);
	    
	} 
}
