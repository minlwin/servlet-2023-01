package com.jdc.shop.model.dto.vo;

import java.util.List;

import com.jdc.shop.model.dto.form.PurchaseAddressForm;

import lombok.Data;

@Data
public class OrderDetailsVo {

	private OrderListVo order;
	private PurchaseAddressForm address;
	private OrderDeliveryVo deilvery;
	private List<OrderItemVo> items;
	private List<OrderMessageVo> messages;
	private List<OrderPaidVo> paids;
}
