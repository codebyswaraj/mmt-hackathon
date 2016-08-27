package com.mmt.trippathon.dao;

import java.util.List;

import com.mmt.trippathon.model.Activity;
import com.mmt.trippathon.model.City;
import com.mmt.trippathon.model.Country;
import com.mmt.trippathon.model.Source;

public interface ActivitySearchDao {

	List<City> getCityList();
	
	List<Country> getCountryList();

	List<Activity> getActivityList();

	List<Source> getSource(String source);

	String getFlightCityCode(String city);
	
	String[] getMMTCityCountryCode(String city);
}
