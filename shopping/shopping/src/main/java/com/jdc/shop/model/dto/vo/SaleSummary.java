package com.jdc.shop.model.dto.vo;

import lombok.Data;

@Data
public class SaleSummary {

	private SaleSummaryItem ordered;
	private SaleSummaryItem cancel;
	private SaleSummaryItem delivered;
	private SaleSummaryItem finished;
	
}
