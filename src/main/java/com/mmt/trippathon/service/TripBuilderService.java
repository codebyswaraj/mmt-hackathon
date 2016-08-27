package com.mmt.trippathon.service;

import com.mmt.trippathon.entity.TripRequest;
import com.mmt.trippathon.entity.TripResponse;

public interface TripBuilderService {

	TripResponse planTrip(TripRequest tripRequest);
}
