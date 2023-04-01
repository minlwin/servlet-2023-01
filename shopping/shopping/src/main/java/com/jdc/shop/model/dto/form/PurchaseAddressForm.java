package com.jdc.shop.model.dto.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class PurchaseAddressForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String phone;
	private String street;
	private String building;

}
