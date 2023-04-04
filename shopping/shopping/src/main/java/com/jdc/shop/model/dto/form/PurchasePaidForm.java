package com.jdc.shop.model.dto.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class PurchasePaidForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int paidInfoId;
	private String payment;
	private String accountNumber;
	private String accountName;
	private int amount;
	private String screenShoot;

}
