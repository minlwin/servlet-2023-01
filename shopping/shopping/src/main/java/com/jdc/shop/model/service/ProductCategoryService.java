package com.jdc.shop.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.CategoryForm;

public class ProductCategoryService {

	private DataSource dataSource;

	public ProductCategoryService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public void save(int productId, int [] categories) {
		
		var deleteSql = "delete from product_category where product_id = ?";
		var sql = "insert into product_category(product_id, category_id) values (?, ?)";

		try (var conn = dataSource.getConnection(); 
				var delete = conn.prepareStatement(deleteSql);
				var stmt = conn.prepareStatement(sql)) {
			
			delete.setInt(1, productId);
			delete.executeUpdate();
			
			for(int cat : categories) {
				stmt.setInt(1, productId);
				stmt.setInt(2, cat);
				stmt.addBatch();
			}
			
			stmt.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<CategoryForm> findCategoriesByProduct(int id) {
		
		List<CategoryForm> list = new ArrayList<>();
		var sql = "select * from category c join product_category p on p.category_id = c.id where p.product_id = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var data = new CategoryForm();
				data.setId(rs.getInt("id"));
				data.setName(rs.getString("name"));
				list.add(data);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
