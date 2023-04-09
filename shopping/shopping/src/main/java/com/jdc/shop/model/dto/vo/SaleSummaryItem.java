package com.jdc.shop.model.dto.vo;

import lombok.Data;

@Data
public class SaleSummaryItem {
	
	private String status;
	private long currentMonth;
	private long total;
}
