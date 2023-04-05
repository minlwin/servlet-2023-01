package com.jdc.shop.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.PurchaseAddressForm;

public class AddressService {

	private DataSource dataSource;

	public AddressService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public List<PurchaseAddressForm> findAddressForCustomer(int id) {
		
		List<PurchaseAddressForm> list = new ArrayList<>();
		
		var sql = "select * from address where account_id = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getData(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public PurchaseAddressForm findAddressById(int id) {
		var sql = "select * from address where id = ?";
		
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
	
	private PurchaseAddressForm getData(ResultSet rs) throws SQLException {
		var dto = new PurchaseAddressForm();
		dto.setId(rs.getInt("id"));
		dto.setName(rs.getString("name"));
		dto.setPhone(rs.getString("phone"));
		dto.setStreet(rs.getString("street"));
		dto.setBuilding(rs.getString("building"));
		return dto;
	}

	public int create(int customerId, PurchaseAddressForm address) {
		
		var sql = "insert into address (account_id, name, phone, building, street) values (?, ?, ?, ?, ?)";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setInt(1, customerId);
			stmt.setString(2, address.getName());
			stmt.setString(3, address.getPhone());
			stmt.setString(4, address.getBuilding());
			stmt.setString(5, address.getStreet());
			
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

}
