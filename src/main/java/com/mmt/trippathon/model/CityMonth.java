package com.mmt.trippathon.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table(value = "city_month")
public class CityMonth extends Model{

	static {
		validatePresenceOf("id", "city_id", "month");
	}
}
