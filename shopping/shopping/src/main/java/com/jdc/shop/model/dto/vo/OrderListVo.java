package com.jdc.shop.model.dto.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderListVo {

	private int id;
	private int customerId;
	private LocalDate orderDate;
	private String customerName;
	private String status;
	private String remark;
	private int totalAmount;
}
