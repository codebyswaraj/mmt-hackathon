package com.mmt.trippathon.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.trippathon.entity.ActivityResponse;
import com.mmt.trippathon.entity.TripRequest;
import com.mmt.trippathon.entity.TripResponse;
import com.mmt.trippathon.service.ActivitySearchService;
import com.mmt.trippathon.service.DataLoaderService;
import com.mmt.trippathon.service.TripBuilderService;

@RestController
public class ActivityController {

	@Autowired
	private DataLoaderService dataLoaderService;

	@Autowired
	private ActivitySearchService activitySearchService;

	@Autowired
	private TripBuilderService tripBuilderService;

	@RequestMapping(value = "/load/{source}", method = RequestMethod.GET)
	public @ResponseBody Boolean loadData(@PathVariable String source) {
		dataLoaderService.loadDataToDB(source);
		return true;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody List<ActivityResponse> searchForActivity(@RequestParam("activity") String activity) {

		try {
			return activitySearchService.searchCityBasedOnActivity(activity);
		} catch (SQLException e) {
			System.out.println("Exception while fetching result");
		}
		return null;
	}

	@RequestMapping(value = "/package", method = RequestMethod.GET)
	public @ResponseBody TripResponse getBestFare(@RequestParam("fromCity") String fromCity,
			@RequestParam("toCity") String toCity, @RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "maxBudget", required = false) String maxBudget) {

		TripRequest request = new TripRequest(fromCity, toCity, Integer.valueOf(maxBudget == null ? "0" : maxBudget),
				Integer.valueOf(month == null ? "0" : month));
		return tripBuilderService.planTrip(request);
	}
}
