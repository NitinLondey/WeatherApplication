package com.example.weatherapplication.controller;


import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import com.example.weatherapplication.model.WeatherAppdeatils;
import com.example.weatherapplication.service.WeatherService;
import com.example.weatherapplication.utility.ConfigurationUtility;

@Controller
public class MainController {

	@Autowired
	private ConfigurationUtility config;

	@Autowired(required = true)
	private WeatherService service;

	public void setService(WeatherService service) {
		this.service = service;
	}

	@GetMapping("/")
	public ModelAndView getCurrentLocation(HttpSession session, Model mdoel) {
		ModelAndView indexx = new ModelAndView("index");

		return indexx;

	}

	@GetMapping("/reset")
	public DeferredResult<ResponseEntity<WeatherAppdeatils>> resetPassword(HttpSession session,
			@RequestParam(value = "lat", required = false) double lat,
			@RequestParam(value = "longgi", required = false) double longi) {

this.service.setlat(lat);
this.service.setlongi(longi);

		this.service.setdata();

		DeferredResult<ResponseEntity<WeatherAppdeatils>> response = new DeferredResult<ResponseEntity<WeatherAppdeatils>>();
		ForkJoinPool.commonPool().submit(() -> {
			System.out.println("Processing in separate thread: " + Thread.currentThread().getName());
			WeatherAppdeatils www = new WeatherAppdeatils();
			www.setAppDetail(this.service.getCurrentTemp());

			ResponseEntity<WeatherAppdeatils> en = new ResponseEntity<WeatherAppdeatils>(www, HttpStatus.OK);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			response.setResult(en);
		});
		return response;

	}

	
	@GetMapping("/address")

	public DeferredResult<ResponseEntity<WeatherAppdeatils>> getAddress(HttpSession session, Model model,
			@RequestParam(value = "add", required = false) String add) {

		
		String url = config.getProperty("weather.api.url");
		String key = config.getProperty("weather.api.key");
		this.service.setForecastAddress(add,url,key);

		DeferredResult<ResponseEntity<WeatherAppdeatils>> response = new DeferredResult<ResponseEntity<WeatherAppdeatils>>();
		ForkJoinPool.commonPool().submit(() -> {
			System.out.println("Processing in separate thread: " + Thread.currentThread().getName());
			WeatherAppdeatils www = new WeatherAppdeatils();
			www.setAppDetail(this.service.getforcastOnAddress());

			ResponseEntity<WeatherAppdeatils> en = new ResponseEntity<WeatherAppdeatils>(www, HttpStatus.OK);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			response.setResult(en);
		});
		return response;

	}

	
}
