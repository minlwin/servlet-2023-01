package com.jdc.shop.model.dto.vo;

import java.util.List;

import com.jdc.shop.model.dto.form.PurchaseAddressForm;

import lombok.Data;

@Data
public class OrderDetailsVo {

	private OrderListVo order;
	private PurchaseAddressForm address;
	private OrderDeliveryVo delivery;
	private List<OrderItemVo> items;
	private List<OrderMessageVo> messages;
	private List<OrderPaidVo> paids;
	
	public int getQuantity() {
		return items.stream().mapToInt(OrderItemVo::getQuantity).sum();
	}
	
	public int getTotal() {
		return items.stream().mapToInt(OrderItemVo::getTotal).sum();
	}
	
	public int getTotalPaid() {
		return paids.stream().mapToInt(OrderPaidVo::getAmount).sum();
	}
}
