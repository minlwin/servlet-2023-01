package com.jdc.shop.model.dto.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Photo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String photo;
	private boolean cover;
}
