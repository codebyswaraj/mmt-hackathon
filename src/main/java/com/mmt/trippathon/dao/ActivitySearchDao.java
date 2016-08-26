package com.mmt.trippathon.dao;

import java.util.List;

import com.mmt.trippathon.model.Activity;
import com.mmt.trippathon.model.City;

public interface ActivitySearchDao {

	List<City> getCityList();

	List<Activity> getActivityList();
}
