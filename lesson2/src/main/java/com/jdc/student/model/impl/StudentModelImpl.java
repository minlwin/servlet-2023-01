package com.jdc.student.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdc.student.model.ConnectionManager;
import com.jdc.student.model.Student;
import com.jdc.student.model.StudentModel;

public class StudentModelImpl implements StudentModel{

	@Override
	public int create(Student s) {
		
		var sql = "insert into student (name, phone, email) values (?, ?, ?)";
		
		try(var conn = ConnectionManager.open();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getPhone());
			stmt.setString(3, s.getEmail());
			
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Student> search(String name) {
		
		List<Student> list = new ArrayList<>();
		
		var sql = new StringBuffer("select * from student");
		
		if(null != name && !name.isBlank()) {
			sql.append(" where lower(name) like ?");
		}

		try (var conn = ConnectionManager.open(); 
				var stmt = conn.prepareStatement(sql.toString())) {

			if(null != name && !name.isBlank()) {
				stmt.setString(1, name.toLowerCase().concat("%"));
			}
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getRow(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Student getRow(ResultSet rs) throws SQLException {
		var data = new Student();
		data.setId(rs.getInt("id"));
		data.setName(rs.getString("name"));
		data.setPhone(rs.getString("phone"));
		data.setEmail(rs.getString("email"));
		return data;
	}

	@Override
	public Student findById(int id) {
		var sql = "select * from student where id = ?";

		try (var conn = ConnectionManager.open(); 
				var stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			
			if(rs.next()) {
				return getRow(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(int id, Student s) {

		var sql = "update student set name = ?, phone = ?, email = ? where id = ?";

		try (var conn = ConnectionManager.open(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getPhone());
			stmt.setString(3, s.getEmail());
			stmt.setInt(4, id);
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {

		var sql = "delete from student where id = ?";

		try (var conn = ConnectionManager.open(); 
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
