package com.jdc.shop.model.dto.vo;

import java.util.List;

import com.jdc.shop.model.dto.form.CategoryForm;

import lombok.Data;

@Data
public class ProductListVo {
	
	private ProductVo product;
	private List<CategoryForm> categories;

}
