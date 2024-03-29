package com.jdc.shop.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.shop.model.dto.form.ProductForm;
import com.jdc.shop.model.dto.page.PageResult;
import com.jdc.shop.model.dto.vo.ProductDetailsVo;
import com.jdc.shop.model.dto.vo.ProductListVo;
import com.jdc.shop.model.dto.vo.ProductPublicVo;
import com.jdc.shop.model.dto.vo.ProductVo;
import com.jdc.shop.utilities.Strings;

public class ProductService {

	private DataSource dataSource;
	private ProductCategoryService categories;
	private ProductFeatureService features;
	private ProductPhotoService photos;
	
	private static final String SELECT_PRODUCT  = """
			select distinct p.id, p.name, p.price, p.brand, p.description, p.sold_out  
			from product p 
			join product_category pc on p.id = pc.product_id 
			join category c on pc.category_id = c.id 
			where 1 = 1""";

	private static final String COUNT_PRODUCT  = """
			select count(distinct(p.id)) from product p 
			join product_category pc on p.id = pc.product_id 
			join category c on pc.category_id = c.id 
			where 1 = 1""";

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

	public PageResult<ProductListVo> searchForAdmin(String category, String keyword, int page, int size) {
		
		List<ProductListVo> list = new ArrayList<>();
		
		var result = new PageResult<ProductListVo>();
		result.setList(list);
		result.setCurrentPage(page);
		result.setPageSize(size);
		
		var sql = new StringBuffer(SELECT_PRODUCT);
		var params = new ArrayList<>();
		
		var count = new StringBuffer(COUNT_PRODUCT);
		var countParams = new ArrayList<>();
		
		if(Strings.isNotBlanck(category)) {
			sql.append(" and lower(c.name) like ?");
			count.append(" and lower(c.name) like ?");
			
			params.add(category.toLowerCase().concat("%"));
			countParams.add(category.toLowerCase().concat("%"));
		}
		
		if(Strings.isNotBlanck(keyword)) {
			sql.append(" and (lower(p.name) like ? or lower(p.brand) like ?)");
			count.append(" and (lower(p.name) like ? or lower(p.brand) like ?)");

			params.add(keyword.toLowerCase().concat("%"));
			params.add(keyword.toLowerCase().concat("%"));

			countParams.add(keyword.toLowerCase().concat("%"));
			countParams.add(keyword.toLowerCase().concat("%"));
		}
		
		if(page > 0 && size > 0) {
			sql.append(" limit ?, ?");
			params.add((page -1) * size);
			params.add(size);
		}
 
		try (var conn = dataSource.getConnection(); 
				var countStmt = conn.prepareStatement(count.toString());
				var stmt = conn.prepareStatement(sql.toString())) {
			
			for(var i = 0; i < countParams.size(); i ++) {
				countStmt.setObject(i + 1, countParams.get(i));
			}
			
			var countRs = countStmt.executeQuery();
			
			while(countRs.next()) {
				result.setTotalCount(countRs.getLong(1));
				break;
			}
			
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
		
		
		return result;
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

	public PageResult<ProductPublicVo> search(String keyword, int page, int size) {
		var list = new ArrayList<ProductPublicVo>();
		var result = new PageResult<ProductPublicVo>();
		
		result.setList(list);
		result.setCurrentPage(page);
		result.setPageSize(size);
		
		var sql = new StringBuffer(SELECT_PRODUCT);
		var params = new ArrayList<>();

		var count = new StringBuffer(COUNT_PRODUCT);
		var countParams = new ArrayList<>();

		if(Strings.isNotBlanck(keyword)) {
			sql.append(" and (lower(p.name) like ? or lower(p.brand) like ?)");
			params.add(keyword.toLowerCase().concat("%"));
			params.add(keyword.toLowerCase().concat("%"));

			count.append(" and (lower(p.name) like ? or lower(p.brand) like ?)");
			countParams.add(keyword.toLowerCase().concat("%"));
			countParams.add(keyword.toLowerCase().concat("%"));
		}
		
		if(page > 0 && size > 0) {
			sql.append(" limit ?, ?");
			params.add((page -1) * size);
			params.add(size);
		}
		
		try (var conn = dataSource.getConnection(); 
				var countStmt = conn.prepareStatement(count.toString());
				var stmt = conn.prepareStatement(sql.toString())) {

			for(var i = 0; i < countParams.size(); i ++) {
				countStmt.setObject(i + 1, countParams.get(i));
			}
			
			var countRs = countStmt.executeQuery();
			
			while(countRs.next()) {
				result.setTotalCount(countRs.getLong(1));
				break;
			}
			
			
			for(var i = 0; i < params.size(); i ++) {
				stmt.setObject(i + 1, params.get(i));
			}
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				var vo = new ProductPublicVo();
				vo.setProduct(getVoData(rs));
				vo.setCategories(categories.findCategoriesByProduct(vo.getProduct().getId()));
				vo.setPhotos(photos.findPhotoByProduct(vo.getProduct().getId()));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


}
