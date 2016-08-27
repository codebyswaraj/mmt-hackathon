package com.mmt.trippathon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.trippathon.api.impl.FlightApiClient;
import com.mmt.trippathon.api.impl.HotelApiClient;
import com.mmt.trippathon.entity.TripRequest;
import com.mmt.trippathon.entity.TripResponse;
import com.mmt.trippathon.service.TripBuilderService;

@Service
public class TripBuilderServiceImpl implements TripBuilderService {

	@Autowired
	private FlightApiClient flightApiClient;
	
	@Autowired
	private HotelApiClient hotelApiClient;
	
	
	@Override
	public TripResponse planTrip(TripRequest tripRequest) {
		
		
		return new TripResponse(flightApiClient.getRateFromApi(tripRequest),hotelApiClient.getRateFromApi(tripRequest));
	}

}
