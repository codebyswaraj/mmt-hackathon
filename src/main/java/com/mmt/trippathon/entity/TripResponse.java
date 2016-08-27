package com.mmt.trippathon.entity;

public class TripResponse {

	private Double flight;

	private Double hotel;

	public TripResponse() {

	}

	public TripResponse(Double flight, Double hotel) {
		super();
		this.flight = flight;
		this.hotel = hotel;
	}

	public Double getFlight() {
		return flight;
	}

	public void setFlight(Double flight) {
		this.flight = flight;
	}

	public Double getHotel() {
		return hotel;
	}

	public void setHotel(Double hotel) {
		this.hotel = hotel;
	}

}
