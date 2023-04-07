package com.jdc.shop.model.dto.vo;

import lombok.Data;

@Data
public class OrderItemVo {

	private int productId;
	private String product;
	private String brand;
	private String image;
	private int unitPrice;
	private int quantity;
	
	public String getProductName() {
		return product;
	}
	
	public int getTotal() {
		return unitPrice * quantity;
	}
}
