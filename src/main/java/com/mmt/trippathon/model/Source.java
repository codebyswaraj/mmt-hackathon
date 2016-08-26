package com.mmt.trippathon.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table(value = "source")
public class Source extends Model{

	static {
		validatePresenceOf("id", "name", "url");
	}
}
