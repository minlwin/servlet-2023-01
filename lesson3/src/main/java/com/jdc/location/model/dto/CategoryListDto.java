package com.jdc.location.model.dto;

public record CategoryListDto(
		int id,
		String name,
		String burmese,
		long divisionCount,
		long townshipCount
		) {

}
