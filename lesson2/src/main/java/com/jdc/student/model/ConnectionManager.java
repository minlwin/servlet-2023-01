package com.jdc.student.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static final String URL = "jdbc:mysql://localhost:3306/lesson2";
	private static final String USER = "lesson2";
	private static final String PASS = "lesson2";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection open() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
