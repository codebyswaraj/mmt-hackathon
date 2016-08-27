package com.mmt.trippathon.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table(value = "city_activity")
public class CityActivity extends Model {

	static {
		validatePresenceOf("id", "city_id", "activity_id", "rank", "source_id");
	}
}
