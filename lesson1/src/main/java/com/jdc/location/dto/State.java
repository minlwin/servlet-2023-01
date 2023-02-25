package com.jdc.location.dto;

public record State(
		int id,
		String name,
		String capital,
		String region
		) {

	public State(String name, String capital, String region) {
		this(0, name, capital, region);
	}
}
