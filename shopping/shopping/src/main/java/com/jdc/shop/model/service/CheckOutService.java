package com.jdc.shop.model.service;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import com.jdc.shop.utilities.ShoppingCart;

public class CheckOutService {
	
	private DataSource dataSource;
	private AddressService addressService;
	private OrderItemService itemService;
	private OrderPaidService paidService;
	
	public CheckOutService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		addressService = new AddressService(dataSource);
		itemService = new OrderItemService(dataSource);
		paidService = new OrderPaidService(dataSource);
	}

	public int purchase(int customerId, ShoppingCart cart) {
		
		
		var sql = "insert into purchase (account_id, purchase_date, status, address_id) value (?, ?, ?, ?)";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			// Create Shipping Address
			var addressId = cart.getAddress().getId();
			
			if(addressId == 0) {
				addressId = addressService.create(customerId, cart.getAddress());
			}

			// Create Purchase
			stmt.setInt(1, customerId);
			stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setString(3, "Ordered");
			stmt.setInt(4, addressId);
			
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				var purchaseId = rs.getInt(1);
				
				// Create Purchase Items
				itemService.create(purchaseId, cart.getItems());
				
				// Create Paid Informations
				paidService.create(purchaseId, cart.getPaidInformations());
				
				cart.clear();
				
				return purchaseId;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
