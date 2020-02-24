package com.example.weatherapplication;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.createam.heroku.https.HttpsEnforcer;


@Configuration
public class APICOnfig {
    @Bean
    public Filter httpsEnforcerFilter(){
        return new HttpsEnforcer();
    }
}
