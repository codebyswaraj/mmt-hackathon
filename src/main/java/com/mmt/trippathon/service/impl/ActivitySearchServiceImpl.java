package com.mmt.trippathon.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.trippathon.connection.DBConnection;
import com.mmt.trippathon.entity.ActivityResponse;
import com.mmt.trippathon.service.ActivitySearchService;

@Service
public class ActivitySearchServiceImpl implements ActivitySearchService {

	@Autowired
	private DBConnection dbConnection;

	@Override
	public List<ActivityResponse> searchCityBasedOnActivity(String requestAct) throws SQLException {

		StringBuilder sb = new StringBuilder(
				"select city.name as city,cact.activity as activity,cmnth.month as month from city_activity cact join city city on city.id=cact.city_id");
		sb.append(" join city_month cmnth on city.id=cmnth.city_id where city.id in (");
		sb.append("select c.id from city_activity ca join city c on ca.city_id=c.id");
		sb.append(" where ca.activity=").append("'").append(requestAct).append("');");
		Connection conn = dbConnection.getDBConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sb.toString());
		Map<String, ActivityResponse> responseMap = new HashMap<>();
		while (rs.next()) {
			String city = rs.getString("city");
			String activity = rs.getString("activity");
			Integer month = rs.getInt("month");
			ActivityResponse response;
			if (responseMap.containsKey(city)) {
				response = responseMap.get(city);
			} else {
				response = new ActivityResponse(new ArrayList<>(), city, new ArrayList<>());
			}
			response.getMonth().add(month);
			response.getActivity().add(activity);
			responseMap.put(city, response);
		}
		return (List<ActivityResponse>) responseMap.values();
	}

}
