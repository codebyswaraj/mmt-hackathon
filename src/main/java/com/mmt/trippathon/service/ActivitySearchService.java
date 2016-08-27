package com.mmt.trippathon.service;

import java.sql.SQLException;
import java.util.List;

import com.mmt.trippathon.entity.ActivityResponse;

public interface ActivitySearchService {

	List<ActivityResponse> searchCityBasedOnActivity(String activity) throws SQLException;
}
