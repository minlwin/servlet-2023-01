package com.jdc.shop.model.dto.vo;

import lombok.Data;

@Data
public class OrderPaidVo {
	
	private int id;
	private int paidInfoId;
	private String payment;
	private String accountNumber;
	private String accountName;
	private int amount;
	private String screenShoot;
}
