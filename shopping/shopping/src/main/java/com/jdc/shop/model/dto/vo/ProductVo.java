package com.jdc.shop.model.dto.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String brand;
	private boolean soldOut;
	private int price;
	private String description;
}
