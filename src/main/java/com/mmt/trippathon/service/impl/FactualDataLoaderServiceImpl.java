package com.mmt.trippathon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.model.Activity;
import com.mmt.trippathon.model.City;
import com.mmt.trippathon.provider.DataLoader;
import com.mmt.trippathon.service.DataLoaderService;

@Service
public class FactualDataLoaderServiceImpl implements DataLoaderService {

	@Autowired
	private ActivitySearchDao activitySearchDao;

	@Override
	public void loadDataToDB(Long sourceId) {

		List<City> cities = activitySearchDao.getCityList();
		List<Activity> activities = activitySearchDao.getActivityList();
		List<DataLoader> runnables = new ArrayList<>();
		activities.forEach(activity -> {
			int offset = 0;
			while (offset < cities.size()) {

			}
		});

	}

}
