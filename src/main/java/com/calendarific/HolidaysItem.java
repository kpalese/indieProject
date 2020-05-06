package com.calendarific;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class HolidaysItem{

	@JsonProperty("date")
	private Date date;

	@JsonProperty("country")
	private Country country;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("locations")
	private String locations;

	@JsonProperty("type")
	private List<String> type;

	@JsonIgnore
	private String states;

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return date;
	}

	public void setCountry(Country country){
		this.country = country;
	}

	public Country getCountry(){
		return country;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLocations(String locations){
		this.locations = locations;
	}

	public String getLocations(){
		return locations;
	}

	public void setType(List<String> type){
		this.type = type;
	}

	public List<String> getType(){
		return type;
	}

	public void setStates(String states){
		this.states = states;
	}

	public String getStates(){
		return states;
	}

	/**
	 * Returns the date of the holiday as a LocalDate
	 * @return the holiday's date
	 */
	public LocalDate dateToLocalDate() {
		int year = this.getDate().getDatetime().getYear();
		int month = this.getDate().getDatetime().getMonth();
		int day = this.getDate().getDatetime().getDay();

		return LocalDate.of(year, month, day);
	}

	@Override
 	public String toString(){
		return 
			"HolidaysItem{" + 
			"date = '" + date + '\'' + 
			",country = '" + country + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",locations = '" + locations + '\'' + 
			",type = '" + type + '\'' + 
			",states = '" + states + '\'' + 
			"}";
		}
}