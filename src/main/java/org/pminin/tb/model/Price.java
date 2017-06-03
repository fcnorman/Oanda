package org.pminin.tb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

	private String intrument;
	private long time;
	private List<Map<String, Object>> bids;
	private List<Map<String, Object>> asks;
	private String halted;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Prices {
		private List<Price> prices = new ArrayList<>();
	}

	public double getBid() {
		return (double) getBids().get(0).get("price");
	}

	public double getAsk() {
		return (double) getAsks().get(0).get("price");
	}

	public double getSpread() {
		return Math.abs(getAsk() - getBid());
	}
	
	public DateTime getDateTime() {
		return new DateTime(time);
	}
}