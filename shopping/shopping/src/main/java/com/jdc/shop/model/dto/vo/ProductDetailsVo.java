package com.jdc.shop.model.dto.vo;

import java.util.List;

import com.jdc.shop.model.dto.form.CategoryForm;

import lombok.Data;

@Data
public class ProductDetailsVo {

	private ProductVo product;
	private List<CategoryForm> categories;
	private List<Features> features;
	private List<Photo> photos;
}
