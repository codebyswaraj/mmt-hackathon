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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mmt.trippathon.api.ApiClient;
import com.mmt.trippathon.dao.ActivitySearchDao;
import com.mmt.trippathon.entity.TripRequest;

@Service
public class HotelApiClient implements ApiClient {

	@Autowired
	private ActivitySearchDao activitySearchDao;

	@Override
	public Double getRateFromApi(TripRequest tripRequest) {

		String toCity = tripRequest.getToCity();
		Integer month = tripRequest.getMonth();
		String[] mmtResponse = activitySearchDao.getMMTCityCountryCode(toCity);
		if (mmtResponse[0] == null || mmtResponse[1] == null) {
			return 0.0;
		}
		toCity = mmtResponse[0];
		SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
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
		ObjectNode guestNode = JsonNodeFactory.instance.objectNode();
		ArrayNode guests = JsonNodeFactory.instance.arrayNode();
		ArrayNode paxList = JsonNodeFactory.instance.arrayNode();
		ObjectNode paxNode = JsonNodeFactory.instance.objectNode();
		paxNode.put("count", "2").put("ageQualifyingCode", "10");
		paxList.add(paxNode);
		guestNode.put("guestCounts", paxList);
		guests.add(guestNode);
		node.put("checkin", deptDate).put("checkout", returnDate).put("pageNum", 1).put("roomStayCandidates", guests);
		String payload = node.toString();

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost("http://10.96.11.59/makemytrip/site/hotels/search/rs/searchResult/"
				+ mmtResponse[1].trim() + "/" + toCity.trim() + "/MOB");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("Accept", "application/json");
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
			resNode = mapper.readValue(responseStr, JsonNode.class).get("Response");
			if (resNode != null) {
				resNode = resNode.get("searchResponseDTO");
			}
			ArrayNode hotelList = null;
			if (resNode != null) {
				hotelList = (ArrayNode) resNode.get("hotelsList");
			}
			if (hotelList != null) {
				resNode = hotelList.get(0).get("displayFare").get("slashedPrice").get("averagePriceWithTax");
			}
		} catch (IOException e) {
		}
		return resNode == null ? 0.0 : resNode.asDouble();
	}

}
