package com.jdc.shop.utilities;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String role;
	private String loginId;
	private LocalDateTime loginTime;

}
