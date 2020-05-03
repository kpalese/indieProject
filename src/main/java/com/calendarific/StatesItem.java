package com.calendarific;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class StatesItem{

	@JsonProperty("exception")
	private Object exception;

	@JsonProperty("iso")
	private String iso;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	@JsonProperty("abbrev")
	private String abbrev;

	public void setException(Object exception){
		this.exception = exception;
	}

	public Object getException(){
		return exception;
	}

	public void setIso(String iso){
		this.iso = iso;
	}

	public String getIso(){
		return iso;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAbbrev(String abbrev){
		this.abbrev = abbrev;
	}

	public String getAbbrev(){
		return abbrev;
	}

	@Override
 	public String toString(){
		return 
			"StatesItem{" + 
			"exception = '" + exception + '\'' + 
			",iso = '" + iso + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",abbrev = '" + abbrev + '\'' + 
			"}";
		}
}