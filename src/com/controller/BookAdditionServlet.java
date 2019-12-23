package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.BookBean;
import com.dao.BookDao;

/**
 * Servlet implementation class BookAdditionServlet
 */
public class BookAdditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String bookName = request.getParameter("bookName");
		int bookCount = Integer.valueOf(request.getParameter("bookCount"));
		ResultSet rs = null;
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		BookBean b = new BookBean();
		b.setBookName(bookName);
		b.setBookCount(bookCount);
		BookDao bd = new BookDao();
		try {
			bd.addBook(b);
				rs = bd.getBook();
				while(rs.next()) {
					b = new BookBean();
					b.setBookName(rs.getString("bookname"));
					b.setBookCount(rs.getInt("bookcount"));
					bookList.add(b);
				}
			request.setAttribute("bookList", bookList);
			RequestDispatcher rd = request.getRequestDispatcher("AdminHome.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
