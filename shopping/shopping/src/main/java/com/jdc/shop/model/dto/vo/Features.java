package com.jdc.shop.model.dto.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Features implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String value;
}
