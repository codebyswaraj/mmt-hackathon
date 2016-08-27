package com.mmt.trippathon.api;

import com.mmt.trippathon.entity.TripRequest;

public interface ApiClient {

	Double getRateFromApi(TripRequest tripRequest);
}
