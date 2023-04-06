package com.jdc.shop.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.vo.CartItemVo;
import com.jdc.shop.model.dto.vo.OrderItemVo;

public class OrderItemService {

	private DataSource dataSource;
	private ProductService productService;

	public OrderItemService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		productService = new ProductService(dataSource);
	}

	public List<OrderItemVo> findByOrderId(int id) {
		var list = new ArrayList<OrderItemVo>();
		var sql = "select * from purchase_item where purchase_id = ?";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var vo = new OrderItemVo();
				vo.setProductId(rs.getInt("product_id"));
				vo.setQuantity(rs.getInt("quantity"));
				vo.setUnitPrice(rs.getInt("unit_price"));
				
				var product = productService.findById(vo.getProductId());
				vo.setProduct(product.getProduct().getName());
				vo.setBrand(product.getProduct().getBrand());
				vo.setImage(product.getCoverImage());
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void create(int purchaseId, List<CartItemVo> items) {
		
		var sql = "insert into purcahse_item (purchase_id, product_id, quantity, unit_price) value (?, ?, ?, ?)";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			// Create Purchase Items
			for(var item : items) {
				stmt.setInt(1, purchaseId);
				stmt.setInt(2, item.getProduct().getProduct().getId());
				stmt.setInt(3, item.getQuantity());
				stmt.setInt(4, item.getUnitPrice());
				
				stmt.addBatch();
			}
			
			stmt.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
