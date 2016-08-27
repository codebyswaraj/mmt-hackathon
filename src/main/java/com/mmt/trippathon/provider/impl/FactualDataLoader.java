package com.mmt.trippathon.provider.impl;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.provider.DataLoader;

public class FactualDataLoader implements DataLoader {

	private ActivitySearchDao activitySearchDao;
	private String country;
	private String activity;

	public FactualDataLoader() {

	}

	public FactualDataLoader(ActivitySearchDao activitySearchDao, String activity, String country) {
		super();
		this.activitySearchDao = activitySearchDao;
		this.activity = activity;
		this.country = country;
	}

	@Override
	public void run() {

		StringBuilder url = new StringBuilder().append(
				"http://api.v3.factual.com/t/places/facets?KEY=nknUyiGl5wMwpDMNxCLjoswEUaCtKo9KLjoOM0Yw&select=locality&filters={\"$and\":[{\"country\":{\"$eq\":\"");
		url.append(country).append("\"}}]}&q=").append(activity).append("&limit=50");
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {
			HttpGet get = new HttpGet(URLEncoder.encode(url.toString(), "UTF-8"));
			HttpResponse response = httpClient.execute(get);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readValue(response.getEntity().getContent(), JsonNode.class).get("response")
					.get("data").get("locality");
		} catch (IOException e) {
			System.out.println("Exception in Loading Data");
		}
		System.out.println("test");
	}

}
