package com.calendarific;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@JsonProperty("holidays")
	private List<HolidaysItem> holidays;

	public void setHolidays(List<HolidaysItem> holidays){
		this.holidays = holidays;
	}

	public List<HolidaysItem> getHolidays(){
		return holidays;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"holidays = '" + holidays + '\'' + 
			"}";
		}
}