package com.jdc.location.repo;

import java.sql.SQLException;

import com.jdc.location.repo.utils.ConnectionManager;

public class DbUtils {
	
	public static void truncate(String ... tables) {
		
		try(var conn = ConnectionManager.getConnection()) {
			
			for(String table : tables) {
				try(var stmt = conn.prepareStatement("truncate table %s".formatted(table))) {
					stmt.execute();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
