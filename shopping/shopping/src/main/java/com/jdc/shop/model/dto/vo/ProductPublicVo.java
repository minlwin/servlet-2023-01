package com.jdc.shop.model.dto.vo;

import java.util.List;

import com.jdc.shop.model.dto.form.CategoryForm;
import com.jdc.shop.utilities.CoverImageResolver;

import lombok.Data;

@Data
public class ProductPublicVo {

	private ProductVo product;
	private List<CategoryForm> categories;
	private List<Photo> photos;

	public String getCoverImage() {
		return CoverImageResolver.resolve(photos);
	}
}
