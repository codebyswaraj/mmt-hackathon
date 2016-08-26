package com.mmt.trippathon.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table(value = "mmt_data")
public class MMTData extends Model {

	static {
		validatePresenceOf("id", "city_id", "mmt_hotel_search", "mmt_hotel_book", "mmt_flight_search",
				"mmt_flight_book", "country");
	}
}
