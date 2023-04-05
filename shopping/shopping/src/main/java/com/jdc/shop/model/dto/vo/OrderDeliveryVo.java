package com.jdc.shop.model.dto.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderDeliveryVo {

	private int accountId;
	private String delivery;
	private String phone;
	private LocalDate date;
	private String timeFrom;
	private String timeTo;
}
