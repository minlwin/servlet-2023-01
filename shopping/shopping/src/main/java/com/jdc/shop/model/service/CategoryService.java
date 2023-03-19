package com.jdc.shop.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.CategoryForm;

public class CategoryService {

	private static final String INSERT = "insert into category(name) values (?)";
	private static final String UPDATE = "update category set name = ? where id = ?";

	private DataSource dataSource;

	public CategoryService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public void save(CategoryForm form) {
		var sql = form.getId() == 0 ? INSERT : UPDATE;

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, form.getName());
			
			if(form.getId() > 0) {
				stmt.setInt(2, form.getId());
			}
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<CategoryForm> findAll() {
		
		var sql = "select * from category";
		var list = new ArrayList<CategoryForm>();

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
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
