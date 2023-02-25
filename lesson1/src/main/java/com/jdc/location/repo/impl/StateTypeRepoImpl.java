package com.jdc.location.repo.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.location.dto.StateType;
import com.jdc.location.repo.StateTypeRepo;
import com.jdc.location.repo.utils.ConnectionManager;

public class StateTypeRepoImpl implements StateTypeRepo{

	@Override
	public void create(StateType data) {

		try(var conn = ConnectionManager.getConnection();
				var stmt = conn.prepareStatement("insert into state_type values (?, ?)")) {
			
			stmt.setString(1, data.name());
			stmt.setString(2, data.burmeseName());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<StateType> findAll() {
		
		var list = new ArrayList<StateType>();
		
		try(var conn = ConnectionManager.getConnection();
				var stmt = conn.prepareStatement("select * from state_type")) {
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getRow(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public StateType findById(String name) {
		
		try(var conn = ConnectionManager.getConnection();
				var stmt = conn.prepareStatement("select * from state_type where name = ?")) {
			
			stmt.setString(1, name);
			
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return getRow(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void update(StateType data) {
		try(var conn = ConnectionManager.getConnection();
				var stmt = conn.prepareStatement("update state_type set burmeseName = ? where name = ?")) {
			
			stmt.setString(1, data.burmeseName());
			stmt.setString(2, data.name());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String name) {
		try(var conn = ConnectionManager.getConnection();
				var stmt = conn.prepareStatement("delete from state_type where name = ?")) {
			
			stmt.setString(1, name);
		
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private StateType getRow(ResultSet rs) throws SQLException {
		return new StateType(rs.getString("name"), rs.getString("burmeseName"));
	}

}
