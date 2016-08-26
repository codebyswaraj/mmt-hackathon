package com.mmt.trippathon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.trippathon.entity.ActivityRequest;
import com.mmt.trippathon.entity.ActivityResponse;
import com.mmt.trippathon.model.City;
import com.mmt.trippathon.service.DataLoaderService;

@RestController
public class ActivityController {

	@Autowired
	private DataLoaderService dataLoaderService;

	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public @ResponseBody List<ActivityResponse> loadData() {
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
