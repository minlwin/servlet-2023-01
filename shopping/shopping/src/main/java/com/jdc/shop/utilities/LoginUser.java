package com.jdc.shop.utilities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String role;
	private String loginId;
	private LocalDateTime loginTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

}
