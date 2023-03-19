package com.jdc.shop.model.dto.form;

import java.util.Map;

import lombok.Data;

@Data
public class ProductForm {

	private int id;
	private String name;
	private String brand;
	private boolean soldOut;
	private int price;
	private String description;
	
	private int [] categories;
	private Map<String, String> felatures;

}
