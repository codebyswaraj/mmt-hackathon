package com.mmt.trippathon.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TripRequest implements Serializable {

	private static final long serialVersionUID = -4823054133750152165L;

	private String fromCity;

	private String toCity;

	private Integer maxBudget;

	private Integer month;

	public TripRequest() {

	}

	public TripRequest(String fromCity, String toCity, Integer maxBudget, Integer month) {
		super();
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.maxBudget = maxBudget;
		this.month = month;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Integer getMaxBudget() {
		return maxBudget;
	}

	public void setMaxBudget(Integer maxBudget) {
		this.maxBudget = maxBudget;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
