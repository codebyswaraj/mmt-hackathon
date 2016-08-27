package com.mmt.trippathon.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.javalite.activejdbc.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mmt.trippathon.connection.DBConnection;
import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.model.Activity;
import com.mmt.trippathon.model.City;
import com.mmt.trippathon.model.Country;
import com.mmt.trippathon.model.Source;

@Component
public class ActivitySearchDaoImpl implements ActivitySearchDao {

	@Value(value = "${jdbc.url}")
	private String jdbcUrl;

	@Autowired
	private DBConnection dbConnection;

	public void createConnection() {
		Base.open("com.mysql.jdbc.Driver", jdbcUrl, "root", "root");
	}

	@Override
	public List<City> getCityList() {
		if (!Base.hasConnection()) {
			createConnection();
		}
		return City.findAll();
	}

	@Override
	public List<Activity> getActivityList() {
		if (!Base.hasConnection()) {
			createConnection();
		}
		return Activity.findAll();
	}

	@Override
	public List<Country> getCountryList() {
		if (!Base.hasConnection()) {
			createConnection();
		}
		return Country.findAll();
	}

	@Override
	public List<Source> getSource(String source) {
		if (!Base.hasConnection()) {
			createConnection();
		}
		List<Source> sources = Source.where("name=?", source);
		return sources;

	}

	@Override
	public String getFlightCityCode(String city) {

		String cityCode = null;
		Connection conn;
		try {
			conn = dbConnection.getDBConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select airport from cities where name='" + city + "'");
			while (rs.next()) {
				cityCode = rs.getString(1);
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
		}
		return cityCode;
	}

	@Override
	public String[] getMMTCityCountryCode(String city) {

		String cityCode = null;
		String countryCode = null;
		String[] response = new String[2];

		Connection conn;
		try {
			conn = dbConnection.getDBConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select mmtcode,country_code from cities where name='" + city + "'");
			while (rs.next()) {
				cityCode = rs.getString(1);
				countryCode = rs.getString(2);
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
		}
		response[0] = cityCode;
		response[1] = countryCode;
		return response;
	}
}
