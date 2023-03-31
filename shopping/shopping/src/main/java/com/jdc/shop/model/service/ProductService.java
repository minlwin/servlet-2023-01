package com.jdc.shop.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.ProductForm;
import com.jdc.shop.model.dto.vo.ProductDetailsVo;
import com.jdc.shop.model.dto.vo.ProductListVo;
import com.jdc.shop.model.dto.vo.ProductVo;
import com.jdc.shop.utilities.Strings;

public class ProductService {

	private DataSource dataSource;
	private ProductCategoryService categories;
	private ProductFeatureService features;
	private ProductPhotoService photos;

	public ProductService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		categories = new ProductCategoryService(dataSource);
		features = new ProductFeatureService(dataSource);
		photos = new ProductPhotoService(dataSource);
	}

	public ProductDetailsVo findById(int id) {
		var result = new ProductDetailsVo();
		result.setProduct(findVoById(id));
		result.setCategories(categories.findCategoriesByProduct(id));
		result.setFeatures(features.findFeaturesByProduct(id));
		result.setPhotos(photos.findPhotoByProduct(id));
		return result;
	}

	public List<ProductListVo> search(String category, String keyword) {
		
		List<ProductListVo> list = new ArrayList<>();
		
		var sql = new StringBuffer("""
				select p.id, p.name, p.price, p.brand, p.description, p.sold_out  
				from product p 
				join product_category pc on p.id = pc.product_id 
				join category c on pc.category_id = c.id 
				where 1 = 1""");
		var params = new ArrayList<>();
		
		if(Strings.isNotBlanck(category)) {
			sql.append(" and lower(c.name) like ?");
			params.add(category.toLowerCase().concat("%"));
		}
		
		if(Strings.isNotBlanck(keyword)) {
			sql.append(" and (lower(p.name) like ? or lower(p.brand) like ?)");
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
				var vo = new ProductListVo();
				vo.setProduct(getVoData(rs));
				vo.setCategories(categories.findCategoriesByProduct(vo.getProduct().getId()));
				
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	


	private ProductVo findVoById(int id) {
		var sql = "select * from product where id = ?";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return getVoData(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private ProductVo getVoData(ResultSet rs) throws SQLException {
		var vo = new ProductVo();
		vo.setId(rs.getInt("id"));
		vo.setName(rs.getString("name"));
		vo.setSoldOut(rs.getBoolean("sold_out"));
		vo.setPrice(rs.getInt("price"));
		vo.setBrand(rs.getString("brand"));
		vo.setDescription(rs.getString("description"));
		return vo;
	}

	public int save(ProductForm form) {
		
		int id = form.getId();
		
		if(id == 0) {
			id = create(form);
		} else {
			update(form);
		}
		
		categories.save(id, form.getCategories());
		features.save(id, form.getFelatures());
		
		return id;
	}

	private void update(ProductForm form) {

		var sql = "update product set name = ?, price = ?, brand = ?, sold_out = ?, description = ? where id = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, form.getName());
			stmt.setInt(2, form.getPrice());
			stmt.setString(3, form.getBrand());
			stmt.setBoolean(4, form.isSoldOut());
			stmt.setString(5, form.getDescription());
			stmt.setInt(6, form.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int create(ProductForm form) {
		var sql = "insert into product (name, price, brand, sold_out, description) values (?, ?, ?, ?, ?)";

		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, form.getName());
			stmt.setInt(2, form.getPrice());
			stmt.setString(3, form.getBrand());
			stmt.setBoolean(4, form.isSoldOut());
			stmt.setString(5, form.getDescription());
			
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

	public void setSoldOut(int id, boolean soldOut) {
		
		var sql = "update product set sold_out = ? where id = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
			stmt.setBoolean(1, soldOut);
			stmt.setInt(2, id);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ProductDetailsVo> search(String keyword) {

		return null;
	}


}
