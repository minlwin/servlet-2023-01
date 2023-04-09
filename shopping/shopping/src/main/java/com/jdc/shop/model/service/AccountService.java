package com.jdc.shop.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.AccountForm;
import com.jdc.shop.utilities.BusinessException;
import com.jdc.shop.utilities.LoginUser;
import com.jdc.shop.utilities.Strings;

public class AccountService {
	
	private DataSource dataSource;
	
	public AccountService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public LoginUser findLoginUser(String loginId) {
		
		var sql = "select id, name, role from account where email = ?";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, loginId);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var loginUser = new LoginUser();
				loginUser.setId(rs.getInt("id"));
				loginUser.setLoginId(loginId);
				loginUser.setName(rs.getString("name"));
				loginUser.setRole(rs.getString("role"));
				loginUser.setLoginTime(LocalDateTime.now());
				return loginUser;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public AccountForm findById(int id) {
		
		var sql = "select * from account where id = ?";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return getData(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private AccountForm getData(ResultSet rs) throws SQLException {
		var form = new AccountForm();
		form.setId(rs.getInt("id"));
		form.setName(rs.getString("name"));
		form.setRole(rs.getString("role"));
		form.setEmail(rs.getString("email"));
		form.setPhone(rs.getString("phone"));
		return form;
	}

	public int save(AccountForm form) {
		
		if(form.getId() == 0) {
			return create(form, form.getEmail());
		}
		
		return update(form);
	}

	private int update(AccountForm form) {
		var sql = "update account set name = ?, role = ?, phone = ? where id = ?";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, form.getName());
			stmt.setString(2, form.getRole());
			stmt.setString(3, form.getPhone());
			stmt.setInt(4, form.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return form.getId();
	}

	public int create(AccountForm form, String password) {
		
		var sql = "insert into account(name, role, email, password, phone) values (?, ?, ?, ?, ?)";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, form.getName());
			stmt.setString(2, form.getRole());
			stmt.setString(3, form.getEmail());
			stmt.setString(4, password);
			stmt.setString(5, form.getPhone());
			
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public List<AccountForm> search(String role, String keyword) {
		
		List<AccountForm> list = new ArrayList<>();
		
		var sql = new StringBuffer("select * from account where 1 = 1");
		var params = new ArrayList<>();
		
		if(Strings.isNotBlanck(role)) {
			sql.append(" and role = ?");
			params.add(role);
		}
		
		if(Strings.isNotBlanck(keyword)) {
			sql.append(" and (lower(name) like ? or lower(email) like ? or lower(phone) like ?)");
			params.add(keyword.toLowerCase().concat("%"));
			params.add(keyword.toLowerCase().concat("%"));
			params.add(keyword.toLowerCase().concat("%"));
		}

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql.toString())) {
			
			for(var i = 0; i < params.size(); i ++) {
				stmt.setObject(i + 1, params.get(i));
			}
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getData(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public void signUp(String name, String loginId, String password) {
		var form = new AccountForm();
		form.setEmail(loginId);
		form.setName(name);
		form.setRole("Customer");
		
		create(form, password);
	}

	public void changePassword(int id, String oldPass, String newPass) {
		var sql = "update account set password = ? where id = ? and password = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, newPass);
			stmt.setInt(2, id);
			stmt.setString(3, oldPass);
			
			if(stmt.executeUpdate() == 0) {
				throw new BusinessException("Please check your old password.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
