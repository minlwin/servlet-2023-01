package com.jdc.shop.model.service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.sql.DataSource;

import com.jdc.shop.utilities.ShoppingCart;

public class CheckOutService {
	
	private DataSource dataSource;
	private AddressService addressService;
	
	public CheckOutService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		addressService = new AddressService(dataSource);
	}

	public int purchase(int customerId, ShoppingCart cart) {
		
		
		var purchaseSql = "insert into purchase (account_id, purchase_date, status, address_id) value (?, ?, ?, ?)";
		var purchaseItemSql = "insert into purcahse_item (purchase_id, product_id, quentity, unit_price) value (?, ?, ?, ?)";

		try (var conn = dataSource.getConnection(); 
				var purchaseStmt = conn.prepareStatement(purchaseSql, Statement.RETURN_GENERATED_KEYS);
				var purchaseItemStmt = conn.prepareStatement(purchaseItemSql)) {
			
			// Create Shipping Address
			var addressId = cart.getAddress().getId();
			
			if(addressId == 0) {
				addressId = addressService.create(customerId, cart.getAddress());
			}

			// Create Purchase
			purchaseStmt.setInt(1, customerId);
			purchaseStmt.setDate(2, Date.valueOf(LocalDate.now()));
			purchaseStmt.setString(3, "Ordered");
			purchaseStmt.setInt(4, addressId);
			
			purchaseStmt.executeUpdate();
			
			var rs = purchaseStmt.getGeneratedKeys();
			
			if(rs.next()) {
				var purchaseId = rs.getInt(1);
				// Create Purchase Items
				for(var item : cart.getItems()) {
					purchaseItemStmt.setInt(1, purchaseId);
					purchaseItemStmt.setInt(2, item.getProduct().getProduct().getId());
					purchaseItemStmt.setInt(3, item.getQuantity());
					purchaseItemStmt.setInt(4, item.getUnitPrice());
					
					purchaseItemStmt.addBatch();
				}
				
				purchaseItemStmt.executeBatch();
				
				return purchaseId;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
