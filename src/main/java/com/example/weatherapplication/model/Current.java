package com.example.weatherapplication.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Current implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long temperature;
	
	private String summary;
	private String icon;
	
	@JsonProperty("temperature")

	public long getTemperature() {
		return temperature;
	}
	public void setTemperature(long temperature) {
		this.temperature = temperature;
	}
	@JsonProperty("summary")

	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@JsonProperty("icon")

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	

}