package com.jdc.shop.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.PaidInfoForm;
import com.jdc.shop.model.dto.form.PaidInfoForm.Type;

public class PaidInfoService {

	private DataSource dataSource;

	public PaidInfoService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public void save(PaidInfoForm form) {
		if(form.getId() == 0) {
			create(form);
		} else {
			update(form);
		}	
	}
	
	
	private void create(PaidInfoForm form) {
		var sql = """
				insert into paid_info (payment_type, payment_name, account_number, account_name) 
				values (?, ?, ?, ?)""";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, form.getPaymentType().name());
			stmt.setString(2, form.getPaymentName());
			stmt.setString(3, form.getAccountNumber());
			stmt.setString(4, form.getAccountName());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void update(PaidInfoForm form) {

		var sql = """
				update paid_info set payment_type = ?, payment_name = ?, 
				account_number = ?, account_name = ? where id = ?""";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, form.getPaymentType().name());
			stmt.setString(2, form.getPaymentName());
			stmt.setString(3, form.getAccountNumber());
			stmt.setString(4, form.getAccountName());
			stmt.setInt(5, form.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PaidInfoForm findById(int id) {
		
		var sql = "select * from paid_info where id = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
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

	public List<PaidInfoForm> findAll() {

		var list = new ArrayList<PaidInfoForm>();
		
		var sql = "select * from paid_info";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getData(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private PaidInfoForm getData(ResultSet rs) throws SQLException {
		var data = new PaidInfoForm();
		data.setId(rs.getInt("id"));
		data.setPaymentType(Type.valueOf(rs.getString("payment_type")));
		data.setPaymentName(rs.getString("payment_name"));
		data.setAccountNumber(rs.getString("account_number"));
		data.setAccountName(rs.getString("account_name"));
		return data;
	}


	
}
