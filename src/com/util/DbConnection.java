package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection conn = null;
		String username = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:42069/librarymanagement";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		return conn;
		
	}

}
