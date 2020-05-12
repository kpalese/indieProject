package com.calendarific;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Datetime{

	@JsonProperty("month")
	private int month;

	@JsonProperty("year")
	private int year;

	@JsonProperty("day")
	private int day;

	public void setMonth(int month){
		this.month = month;
	}

	public int getMonth(){
		return month;
	}

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setDay(int day){
		this.day = day;
	}

	public int getDay(){
		return day;
	}

	@Override
 	public String toString(){
		return 
			"Datetime{" + 
			"month = '" + month + '\'' + 
			",year = '" + year + '\'' + 
			",day = '" + day + '\'' + 
			"}";
		}
}