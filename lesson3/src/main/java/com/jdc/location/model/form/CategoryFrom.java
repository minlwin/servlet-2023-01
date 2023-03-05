package com.jdc.location.model.form;

public record CategoryFrom(
		int id,
		String name,
		String burmese
		) {
	
	public CategoryFrom(String name, String burmese) {
		this(0, name, burmese);
	}

	public CategoryFrom withId(int id) {
		return new CategoryFrom(id, name, burmese);
	}
}
