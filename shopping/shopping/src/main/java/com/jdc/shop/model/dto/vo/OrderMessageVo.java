package com.jdc.shop.model.dto.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderMessageVo {

	private int id;
	private String message;
	private LocalDateTime sendAt;
	private int senderId;
	private String sender;
	private String role;
}
