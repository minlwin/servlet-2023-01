package com.jdc.location.repo.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.jdc.location.dto.State;
import com.jdc.location.repo.StateRepo;
import com.jdc.location.repo.utils.ConnectionManager;

public class StateRepoImpl implements StateRepo{

	@Override
	public int create(State state) {
		
		String sql = "insert into state (name, region, capital) values (?, ?, ?)";
		
		try(var conn = ConnectionManager.getConnection();
				var stmt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, state.name());
			stmt.setString(2, state.region());
			stmt.setString(3, state.capital());
			
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

	@Override
	public void update(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<State> search(String region, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
