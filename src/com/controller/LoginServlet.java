package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.BookBean;
import com.bean.MyBookBean;
import com.dao.BookDao;
import com.dao.IssuerDao;
import com.util.DbConnection;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		ArrayList<MyBookBean> myBookList = new ArrayList<MyBookBean>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String value = null;
		ResultSet rs = null;
		String myBookName = null;
		IssuerDao id = new IssuerDao();
		try {
			value = id.issuerView(username);
			if(value.contains(username) && value.contains(password)) {
				BookDao bd = new BookDao();
				rs = bd.getBook();
				while(rs.next()) {
					BookBean b = new BookBean();
					b.setBookName(rs.getString("bookname"));
					b.setBookCount(rs.getInt("bookcount"));
					bookList.add(b);
				}
				rs = bd.getMyBooks(username);
				
				while(rs.next()) {
					myBookName = rs.getString("myBook");
				}
				String bookNames[] = myBookName.split(",");
				for( String b : bookNames) {
					MyBookBean mb = new MyBookBean();
					mb.setBookName(b);
					myBookList.add(mb);
				}
//				myBookList = bd.getMyBooks(username);
//				System.out.println("myBookList --->"+ myBookList);
				
				session.setAttribute("uname", username);
				request.setAttribute("bookList", bookList);
				request.setAttribute("myBookList", myBookList);
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("loginError", "<font color='red'><h2>the username and password doesn't match</h2></font>");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
