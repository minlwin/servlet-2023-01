package com.jdc.location.model;

import java.util.List;

import com.jdc.location.model.dto.CategoryListDto;
import com.jdc.location.model.form.CategoryFrom;

public class CategoryModel {
	
	public int save(CategoryFrom form) {
		return 0;
	}
	
	public List<CategoryListDto> findAll() {
		return List.of(
			new CategoryListDto(1, "State", "တိုင်းဒေသကြီး", 7, 45),
			new CategoryListDto(2, "Division", "ပြည်နယ်", 7, 45),
			new CategoryListDto(3, "Union Teratory", "အုပ်ချူပ်ရေးဒေသကြီး", 1, 6));
	}
}
