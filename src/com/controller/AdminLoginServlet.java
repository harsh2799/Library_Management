package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.BookBean;
import com.dao.BookDao;

/**
 * Servlet implementation class AdminLoginServlet
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ServletConfig sg = getServletConfig();
		ResultSet rs = null;
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		
		if(username.equals(sg.getInitParameter("username")) && password.equals(sg.getInitParameter("password"))) {
			BookDao bd = new BookDao();
			try {
				rs = bd.getBook();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(rs.next()) {
					BookBean b = new BookBean();
					b.setBookName(rs.getString("bookname"));
					b.setBookCount(rs.getInt("bookcount"));
					bookList.add(b);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("bookList", bookList);
			RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("loginError", "<b><font color='red'>Wrong Credentials!</font></b>");
			RequestDispatcher rd = request.getRequestDispatcher("AdminLogin.jsp");
			rd.forward(request, response);
			
		}
		
	}

}
