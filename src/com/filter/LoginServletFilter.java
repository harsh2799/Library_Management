package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServletFilter implements Filter {

	public void destroy() {
		System.out.println("Filter Destroyed");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean isError = false;
		
		if(username.trim().length() == 0 || username == null) {
			isError = true;
			request.setAttribute("usernameError", "<font color='red'><b>Kindly Enter Username!!</b></font>");
		}
		else {
			request.setAttribute("usernameValue", username);
		}
		
		
		if(password == null || password.trim().length() == 0) {
			isError = true;
			request.setAttribute("passwordError", "<font color='red'><b>Kindly Enter Password!!</b></font>");
		}
				
		if(isError == true) {
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
		else {
			filterChain.doFilter(request, response);
		}
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter Initialized");
	}

}
