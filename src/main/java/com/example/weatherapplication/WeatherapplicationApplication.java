package com.example.weatherapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication (scanBasePackages={
		"com.example.weatherapplication", "com.example.weatherapplication.service",
		"com.example.weatherapplication.controller",
		"com.example.weatherapplication.model"})
@EnableScheduling
public class WeatherapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherapplicationApplication.class, args);
	}

}
