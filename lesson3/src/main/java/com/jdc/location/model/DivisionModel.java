package com.jdc.location.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.location.model.dto.DivisionListDto;
import com.jdc.location.model.form.DivisionForm;

public class DivisionModel {

	private DataSource dataSource;

	public DivisionModel(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public int save(DivisionForm form) {
		if(form.id() == 0) {
			return create(form);
		}
		
		// Update
		var sql = """
				update division set name = ?, burmese = ?, capital = ?,
				category_id = ? where id = ?
				""";
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, form.name());
			stmt.setString(2, form.burmese());
			stmt.setString(3, form.capital());
			stmt.setInt(4, form.categoryId());
			stmt.setInt(5, form.id());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return form.id();
	}
	
	private int create(DivisionForm form) {
		var sql = """
				insert into division(name, burmese, capital, category_id) 
				values (?, ?, ?, ?)
				""";
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, form.name());
			stmt.setString(2, form.burmese());
			stmt.setString(3, form.capital());
			stmt.setInt(4, form.categoryId());
			
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<DivisionListDto> findAll() {
		
		List<DivisionListDto> list = new ArrayList<>();
		
		var sql = """
				select d.id, d.name, d.burmese, d.capital, c.id cId, c.name cName 
				from division d join category c on c.id = d.category_id 
				order by d.id""";
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getData(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return list;
	}

	private DivisionListDto getData(ResultSet rs) throws SQLException {
		return new DivisionListDto(rs.getInt("id"), 
				rs.getString("name"), 
				rs.getString("burmese"), 
				rs.getString("capital"), 
				rs.getInt("cId"), 
				rs.getString("cName"));
	}

	public DivisionForm findFormById(int id) {
		
		var sql = "select * from division where id = ?";
		
		try (var conn = dataSource.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return new DivisionForm(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("burmese"), 
						rs.getString("capital"), 
						rs.getInt("category_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
