package com.jdc.shop.model.dto.vo;

import lombok.Data;

@Data
public class ProductVo {

	private int id;
	private String name;
	private String brand;
	private boolean soldOut;
	private int price;
	private String description;
}
