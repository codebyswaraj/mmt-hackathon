package com.mmt.trippathon.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table(value = "country")
public class Country extends Model {

	static {
		validatePresenceOf("id", "code", "name");
	}
}
