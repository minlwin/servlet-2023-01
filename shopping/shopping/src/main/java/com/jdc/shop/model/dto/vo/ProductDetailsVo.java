package com.jdc.shop.model.dto.vo;

import java.util.List;

import com.jdc.shop.model.dto.form.CategoryForm;
import com.jdc.shop.utilities.CoverImageResolver;

import lombok.Data;

@Data
public class ProductDetailsVo {

	private ProductVo product;
	private List<CategoryForm> categories;
	private List<Features> features;
	private List<Photo> photos;
	
	public List<CategoryForm> getSelectedCategory(List<CategoryForm> all) {
		
		for(var cat : all) {
			if(categories.contains(cat)) {
				cat.setCheck(true);
			}
		}
		
		return all;
	}
	
	public String getCoverImage() {
		return CoverImageResolver.resolve(photos);
	}
}
