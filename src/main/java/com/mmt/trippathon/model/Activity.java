package com.mmt.trippathon.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table(value="activity")
public class Activity extends Model{

	static {
		validatePresenceOf("id", "name", "sub_activity");
	}
}
