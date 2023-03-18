package com.jdc.shop.model.service;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import com.jdc.shop.utilities.LoginUser;

public class AccountService {
	
	private DataSource dataSource;
	
	public AccountService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public LoginUser findLoginUser(String loginId) {
		
		var sql = "select name, role from account where email = ?";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, loginId);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var loginUser = new LoginUser();
				loginUser.setLoginId(loginId);
				loginUser.setName(rs.getString(1));
				loginUser.setRole(rs.getString(2));
				loginUser.setLoginTime(LocalDateTime.now());
				return loginUser;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
