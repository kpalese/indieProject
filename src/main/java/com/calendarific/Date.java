package com.calendarific;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Date{

	@JsonProperty("datetime")
	private Datetime datetime;

	@JsonProperty("iso")
	private String iso;

	public void setDatetime(Datetime datetime){
		this.datetime = datetime;
	}

	public Datetime getDatetime(){
		return datetime;
	}

	public void setIso(String iso){
		this.iso = iso;
	}

	public String getIso(){
		return iso;
	}

	@Override
 	public String toString(){
		return 
			"Date{" + 
			"datetime = '" + datetime + '\'' + 
			",iso = '" + iso + '\'' + 
			"}";
		}
}