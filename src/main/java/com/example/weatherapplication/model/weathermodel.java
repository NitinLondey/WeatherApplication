package com.example.weatherapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class weathermodel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double latitude;
	private double longitude;
	private String timezone;
	private Current current;

	@JsonProperty("currently")

	public Current getCurrent() {
		return current;
	}

	public void setCurrent(Current current) {
		this.current = current;
	}
	@JsonProperty("daily")
	public Daily getDaily() {
		return daily;
	}

	public void setDaily(Daily daily) {
		this.daily = daily;
	}

	private Daily daily;

	@JsonProperty("latitude")
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("longitude")
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@JsonProperty("timezone")
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

}