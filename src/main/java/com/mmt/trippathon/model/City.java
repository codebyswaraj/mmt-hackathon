package com.mmt.trippathon.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table(value="city")
public class City extends Model {

	static {
		validatePresenceOf("id", "name", "code", "country");
	}
}
