package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.IssuerBean;
import com.util.DbConnection;

public class IssuerDao {
	
	Connection conn = null;
	
	public void issuerInsert(IssuerBean issuer) throws ClassNotFoundException, SQLException {
				
		
		conn = DbConnection.getConnection();
		String query = "insert into issuer(username,password) values('"+issuer.getUsername()+"','"+issuer.getPassword()+"');";
		
		Statement stmt = conn.createStatement();
		stmt.execute(query);
		conn.close();
	}
	
	public String issuerView(String name) throws SQLException, ClassNotFoundException {
		conn = DbConnection.getConnection();
		String uname = null;
		String pass = null;
		String query = "select username,password from issuer where username ='"+name+"';";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery(query);
		while(rs.next()) {
			uname = rs.getString("username");
			pass = rs.getString("password");
		}
		
		return uname+":"+pass;
		
	}

}
