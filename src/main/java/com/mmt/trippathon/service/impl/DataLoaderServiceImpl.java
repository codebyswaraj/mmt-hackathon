package com.mmt.trippathon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.model.Source;
import com.mmt.trippathon.service.DataLoaderService;
import com.mmt.trippathon.threadpool.ActivityThreadPool;

@Service
public class DataLoaderServiceImpl implements DataLoaderService {

	@Autowired
	private ActivitySearchDao activitySearchDao;

	@Autowired
	private ActivityThreadPool activityThreadPool;

	@Override
	public void loadDataToDB(String source) {

		List<Source> sources = activitySearchDao.getSource(source);
		switch (source) {
		case "Factual":
			FactualDataLoaderServiceImpl dataLoader = new FactualDataLoaderServiceImpl(activitySearchDao,
					activityThreadPool);
			dataLoader.loadActivityPerCountry(sources.get(0).getLong("id"));
			break;
		}

	}
}
