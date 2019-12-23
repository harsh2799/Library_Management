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
import javax.servlet.http.HttpSession;

import com.bean.BookBean;
import com.bean.MyBookBean;
import com.dao.BookDao;

/**
 * Servlet implementation class IssueServlet
 */
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		ArrayList<MyBookBean> myBookList = new ArrayList<MyBookBean>();
		ResultSet rs = null;
		String bookName = request.getParameter("bookname");
		String username = (String) session.getAttribute("uname");
		String myBookName = null;
		Boolean bookAlreadyExist = false;

		BookDao bd = new BookDao();
		try {
			rs = bd.getMyBooks(username);

			while (rs.next()) {
				myBookName = rs.getString("myBook");
			}
			String bookNames[] = myBookName.split(",");
			for (String b : bookNames) {
				MyBookBean mb = new MyBookBean();
				mb.setBookName(b);
				myBookList.add(mb);
			}
			for (MyBookBean b : myBookList) {
				if (b.getBookName().equals(bookName)) {
					bookAlreadyExist = true;
				}
			}
			if (bookAlreadyExist == false) {
				bookList.clear();
				bd.bookIssued(bookName, username);
				rs = bd.getBook();
				while (rs.next()) {
					BookBean b = new BookBean();
					b.setBookName(rs.getString("bookname"));
					b.setBookCount(rs.getInt("bookcount"));
					bookList.add(b);
				}
				rs = bd.getMyBooks(username);

				while (rs.next()) {
					myBookName = rs.getString("myBook");
				}
				String bookNam[] = myBookName.split(",");
				myBookList.clear();
				for (String b : bookNam) {
					MyBookBean mb = new MyBookBean();
					mb.setBookName(b);
					myBookList.add(mb);
				}
				request.setAttribute("bookList", bookList);
				request.setAttribute("myBookList", myBookList);
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
				rs.close();
			}
			else {
				ResultSet rsa = bd.getBook();
				bookList.clear();
				while (rsa.next()) {
					BookBean b = new BookBean();
					b.setBookName(rsa.getString("bookname"));
					b.setBookCount(rsa.getInt("bookcount"));
					bookList.add(b);
				}
				rsa = bd.getMyBooks(username);

				while (rsa.next()) {
					myBookName = rsa.getString("myBook");
				}
				String bookNam[] = myBookName.split(",");
				myBookList.clear();
				for (String b : bookNam) {
					MyBookBean mb = new MyBookBean();
					mb.setBookName(b);
					myBookList.add(mb);
				}
				request.setAttribute("bookList", bookList);
				request.setAttribute("myBookList", myBookList);
				request.setAttribute("alreadyExist", "<font color='red'><b>Book Already Exist</b></font>");
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
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
