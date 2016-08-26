package com.mmt.trippathon.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "activityResponse")
public class ActivityResponse implements Serializable {

	private static final long serialVersionUID = -4871868377464975006L;

	private Integer month;
	private String city;
	private List<String> activity;

	public ActivityResponse() {

	}

	public ActivityResponse(Integer month, String city, List<String> activity) {
		super();
		this.month = month;
		this.city = city;
		this.activity = activity;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getActivity() {
		return activity;
	}

	public void setActivity(List<String> activity) {
		this.activity = activity;
	}

}
