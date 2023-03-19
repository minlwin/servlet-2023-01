package com.jdc.shop.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.vo.Features;

public class ProductFeatureService {

	private DataSource dataSource;
	
	public ProductFeatureService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public void save(int productId, Map<String, String> features) {
		var delSql = "delete from feature where product_id = ?";
		var sql = "insert into feature(product_id, name, value) values (?, ?, ?)";

		try (var conn = dataSource.getConnection(); 
				var delete = conn.prepareStatement(delSql);
				var stmt = conn.prepareStatement(sql)) {
			
			delete.setInt(1, productId);
			delete.executeUpdate();
			
			for(var entry : features.entrySet()) {
				stmt.setInt(1, productId);
				stmt.setString(2, entry.getKey());
				stmt.setString(3, entry.getValue());
				
				stmt.addBatch();
			}
			
			stmt.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public List<Features> findFeaturesByProduct(int productId) {
		
		List<Features> list = new ArrayList<>();
		
		var sql = "select * from feature where product_id = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, productId);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var feature = new Features();
				feature.setName(rs.getString("name"));
				feature.setValue(rs.getString("value"));
				list.add(feature);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
