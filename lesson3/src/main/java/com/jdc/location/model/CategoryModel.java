package com.jdc.location.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.location.model.dto.CategoryListDto;
import com.jdc.location.model.form.CategoryFrom;

public class CategoryModel {
	
	private DataSource dataSource;
	
	public CategoryModel(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int save(CategoryFrom form) {
		return form.id() == 0 ? create(form) : update(form);
	}
	
	private int update(CategoryFrom form) {
		
		var sql = "update category set name = ?, burmese = ? where id = ?";
		
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, form.name());
			stmt.setString(2, form.burmese());
			stmt.setInt(3, form.id());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return form.id();
	}

	private int create(CategoryFrom form) {
		
		var sql = "insert into category (name, burmese) values (?, ?)";
		
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, form.name());
			stmt.setString(2, form.burmese());
			
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

	public List<CategoryListDto> findAll() {
		List<CategoryListDto> list = new ArrayList<>();
		
		var sql = "select * from category";
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getListDto(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private CategoryListDto getListDto(ResultSet rs) throws SQLException {
		var id = rs.getInt("id");
		var name = rs.getString("name");
		var burmese = rs.getString("burmese");
		var divisionCount = findDivisionCountForCategory(id);
		var townshipCount = findTownshipCountForCategory(id);
		return new CategoryListDto(id, name, burmese, divisionCount, townshipCount);
	}

	private long findTownshipCountForCategory(int id) {
		var sql = """
				select count(t.id) from township t 
				join division d on d.id = t.division_id 
				where d.category_id = ?
				""";
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getLong(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private long findDivisionCountForCategory(int id) {
		
		var sql = """
				select count(id) from division where category_id = ? 
				""";
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getLong(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public CategoryFrom findById(int id) {
		var sql = "select * from category where id = ?";
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return new CategoryFrom(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
