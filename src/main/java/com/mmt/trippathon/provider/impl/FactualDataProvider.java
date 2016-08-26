package com.mmt.trippathon.provider.impl;

import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.provider.DataProvider;

public class FactualDataProvider implements DataProvider {

	private ActivitySearchDao activitySearchDao;

	public FactualDataProvider() {

	}

	public FactualDataProvider(ActivitySearchDao activitySearchDao) {
		super();
		this.activitySearchDao = activitySearchDao;
	}

	@Override
	public void run() {

	}

}
