package com.mmt.trippathon.service.impl;

import java.util.List;

import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.model.Activity;
import com.mmt.trippathon.model.Country;
import com.mmt.trippathon.provider.DataLoader;
import com.mmt.trippathon.provider.impl.FactualDataLoader;
import com.mmt.trippathon.threadpool.ActivityThreadPool;

public class FactualDataLoaderServiceImpl {

	private ActivitySearchDao activitySearchDao;

	private ActivityThreadPool activityThreadPool;

	public FactualDataLoaderServiceImpl(ActivitySearchDao activitySearchDao, ActivityThreadPool activityThreadPool) {
		super();
		this.activitySearchDao = activitySearchDao;
		this.activityThreadPool = activityThreadPool;
	}

	public void loadActivityPerCountry(Long sourceId) {

		List<Country> countryList = activitySearchDao.getCountryList();
		List<Activity> activities = activitySearchDao.getActivityList();
		activities.forEach(activity -> {
			countryList.forEach(country -> {
				DataLoader loader = new FactualDataLoader(activitySearchDao, activity.getString("name"),
						country.getString("code"));
				activityThreadPool.getExecutor().execute(loader);
			});
		});
	}

}
