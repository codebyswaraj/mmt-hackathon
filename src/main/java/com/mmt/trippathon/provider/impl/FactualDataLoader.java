package com.mmt.trippathon.provider.impl;

import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.provider.DataLoader;

public class FactualDataLoader implements DataLoader {

	private ActivitySearchDao activitySearchDao;

	public FactualDataLoader() {

	}

	public FactualDataLoader(ActivitySearchDao activitySearchDao) {
		super();
		this.activitySearchDao = activitySearchDao;
	}

	@Override
	public void run() {

	}

}
