package com.mmt.trippathon.api.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mmt.trippathon.api.ApiClient;
import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.entity.TripRequest;

@Service
public class FlightApiClient implements ApiClient {

	@Autowired
	private ActivitySearchDao activitySearchDao;

	@Override
	public Double getRateFromApi(TripRequest tripRequest) {

		String fromCity = tripRequest.getFromCity();
		String toCity = tripRequest.getToCity();
		Integer month = tripRequest.getMonth();
		fromCity = null == fromCity ? "DEL" : activitySearchDao.getFlightCityCode(fromCity);
		toCity = activitySearchDao.getFlightCityCode(toCity);
		if (toCity == null) {
			return 0.0;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String deptDate = null;
		String returnDate = null;
		Calendar calendar = Calendar.getInstance();
		if (month > 0 && month > calendar.get(Calendar.MONTH)) {
			calendar.set(calendar.get(Calendar.YEAR), month - 1, 15);
		} else if (month < calendar.get(Calendar.MONTH)) {
			calendar.set(calendar.get(Calendar.YEAR) + 1, month - 1, 15);
		} else {
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 15);
		}
		deptDate = format.format(calendar.getTime());
		calendar.setTime(new Date());
		if (month > 0 && month > calendar.get(Calendar.MONTH)) {
			calendar.set(calendar.get(Calendar.YEAR), month - 1, 16);
		} else if (month < calendar.get(Calendar.MONTH)) {
			calendar.set(calendar.get(Calendar.YEAR) + 1, month - 1, 16);
		} else {
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 16);
		}
		returnDate = format.format(calendar.getTime());
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("fromCity", fromCity.trim()).put("toCity", toCity.trim()).put("tripType", "R").put("tripTypeDup", "R")
				.put("deptDate", deptDate).put("returnDate", returnDate).put("noOfAdlts", "1").put("noOfChd", "0")
				.put("noOfInfnt", "0").put("classType", "E").put("lob", "Flight");
		String payload = node.toString();
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("http://flights.makemytrip.com/makemytrip/flights-search");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("Accept", "application/json");
		request.addHeader("User-Agent", "googlebot");
		HttpResponse response = null;
		String responseStr = null;
		try {
			request.setEntity(new StringEntity(payload));
			response = client.execute(request);
			if (response == null) {
				return 0.0;
			}
			BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(response.getEntity());

			responseStr = EntityUtils.toString(bufferedHttpEntity);
			EntityUtils.consumeQuietly(response.getEntity());
		} catch (IOException e) {
		}
		ObjectMapper mapper = new ObjectMapper();
		JsonNode resNode = null;
		try {
			resNode = mapper.readValue(responseStr, JsonNode.class).get("rtFilterData");
			if (resNode != null) {
				resNode = resNode.get("cheapestDeptCombo");
			}
		} catch (IOException e) {
		}
		return resNode == null ? 0.0 : resNode.asDouble();
	}

}
