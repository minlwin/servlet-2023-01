package com.jdc.shop.model.dto.form;

import lombok.Data;

@Data
public class PaidInfoForm {

	private int id;
	private Type paymentType;
	private String paymentName;
	private String accountNumber;
	private String accountName;
	
	public enum Type {
		Banking, Mobile
	}
}
