package com.mmt.trippathon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javalite.activejdbc.Base;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.trippathon.entity.ActivityRequest;
import com.mmt.trippathon.entity.ActivityResponse;
import com.mmt.trippathon.model.City;

@RestController
public class ActivityController {

	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public @ResponseBody List<ActivityResponse> loadData() {
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://172.16.43.125/hackathon", "root", "root");
		City c = City.findById(1);
		List<ActivityResponse> list = new ArrayList<ActivityResponse>();
		ActivityResponse response = new ActivityResponse(12, c.getString("name"), Arrays.asList("test", "value"));
		list.add(response);
		return list;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody ActivityResponse searchForActivity(ActivityRequest request) {
		return null;

	}
}