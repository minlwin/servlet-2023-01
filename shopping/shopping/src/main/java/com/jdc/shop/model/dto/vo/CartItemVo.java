package com.jdc.shop.model.dto.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ProductDetailsVo product;
	private int quantity;
	
	public int getProductId() {
		return product.getProduct().getId();
	}
	
	public String getProductName() {
		return product.getProduct().getName();
	}
	
	public String getBrand() {
		return product.getProduct().getBrand();
	}
	
	public int getUnitPrice() {
		return product.getProduct().getPrice();
	}
	
	public int getTotal() {
		return quantity * product.getProduct().getPrice();
	}

	public boolean addOne() {
		quantity ++;
		return true;
	}

	public CartItemVo(ProductDetailsVo product) {
		super();
		this.product = product;
		this.quantity = 1;
	}

	public boolean removeOne() {
		quantity --;
		return quantity == 0;
	}
}
