package com.jdc.shop.model.dto.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class OrderListVo {

	private int id;
	private int customerId;
	private LocalDateTime orderDate;
	private String customerName;
	private String status;
	private String remark;
	private int totalAmount;
	
	public String getDateTime() {
		return orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
}
