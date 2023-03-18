package com.jdc.shop.model.dto.form;

import lombok.Data;

@Data
public class AccountForm {

	private int id;
	private String name;
	private String role;
	private String phone;
	private String email;
}
