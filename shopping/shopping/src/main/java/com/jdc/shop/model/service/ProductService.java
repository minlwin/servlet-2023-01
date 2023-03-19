package com.jdc.shop.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.CategoryForm;
import com.jdc.shop.model.dto.vo.Features;
import com.jdc.shop.model.dto.vo.Photo;
import com.jdc.shop.model.dto.vo.ProductDetailsVo;
import com.jdc.shop.model.dto.vo.ProductListVo;
import com.jdc.shop.model.dto.vo.ProductVo;
import com.jdc.shop.utilities.Strings;

public class ProductService {

	private DataSource dataSource;

	public ProductService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public ProductDetailsVo findById(int id) {
		var result = new ProductDetailsVo();
		result.setProduct(findVoById(id));
		result.setCategories(findCategoriesByProduct(id));
		result.setFeatures(findFeaturesByProduct(id));
		result.setPhotos(findPhotoByProduct(id));
		return result;
	}

	private List<Photo> findPhotoByProduct(int id) {
		List<Photo> list = new ArrayList<>();
		var sql = "select * from image where product_id = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var photo = new Photo();
				photo.setCover(rs.getBoolean("cover"));
				photo.setPhoto(rs.getString("photo"));
				list.add(photo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<Features> findFeaturesByProduct(int id) {
		
		List<Features> list = new ArrayList<>();
		
		var sql = "select * from feature where product_id = ?";

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			
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

	private List<CategoryForm> findCategoriesByProduct(int id) {
		
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
				vo.setCategories(findCategoriesByProduct(vo.getProduct().getId()));
				
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
