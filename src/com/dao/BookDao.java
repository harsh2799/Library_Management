package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.BookBean;
import com.util.DbConnection;

public class BookDao {

	public void addBook(BookBean b) throws ClassNotFoundException, SQLException {

		Connection conn = DbConnection.getConnection();
		String query = "insert into books(bookname,bookcount) values('" + b.getBookName() + "'," + b.getBookCount()
				+ ");";
		Statement stmt = conn.createStatement();
		stmt.execute(query);
		conn.close();
	}

	public ResultSet getBook() throws ClassNotFoundException, SQLException {
		Connection conn = DbConnection.getConnection();
		String query = "select * from books;";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	public ResultSet getMyBooks(String username) throws SQLException, ClassNotFoundException {
		Connection conn = DbConnection.getConnection();
		String query = "select myBook from issuer where username ='" + username + "';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	public void bookIssued(String bookName, String username) throws ClassNotFoundException, SQLException {
		Connection conn = DbConnection.getConnection();
		String bookToCustomer = "update issuer set myBook = concat(myBook,',"+bookName+"') where username='"+username+"';";
		String bookFromDatabase = "update books set bookcount=bookcount-1 where bookname='"+bookName+"';";
		Statement stmt = conn.createStatement();
		stmt.execute(bookFromDatabase);
		stmt.execute(bookToCustomer);
	}
	
	public void bookRevoked(String bookName, String username) throws ClassNotFoundException, SQLException {
		Connection conn = DbConnection.getConnection();
		String bookFromCustomer = "update issuer set myBook = replace(myBook,',"+bookName+"','') where username='"+username+"';";
		String bookToDatabase = "update books set bookcount=bookcount+1 where bookname='"+bookName+"';";
		Statement stmt = conn.createStatement();
		stmt.execute(bookToDatabase);
		stmt.execute(bookFromCustomer);
	}
	
	public void increaseBook(String bookname) throws ClassNotFoundException, SQLException {
		Connection conn = DbConnection.getConnection();
		String increaseBookCount = "update books set bookCount = bookCount + 1 where bookName ='"+ bookname +"';";
		Statement st = conn.createStatement();
		st.execute(increaseBookCount);
	}
}
