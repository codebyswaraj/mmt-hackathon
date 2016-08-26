package com.mmt.trippathon.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.javalite.activejdbc.Base;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.model.Activity;
import com.mmt.trippathon.model.City;

@Component
public class ActivitySearchDaoImpl implements ActivitySearchDao {

	@Value(value = "${jdbc.url}")
	private String jdbcUrl;

	@PostConstruct
	public void createConnection() {
		Base.open("com.mysql.jdbc.Driver", jdbcUrl, "root", "root");
	}

	@Override
	public List<City> getCityList() {
		return City.findAll();
	}

	@Override
	public List<Activity> getActivityList() {
		return Activity.findAll();
	}
}
