<%@page import="com.bean.MyBookBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bean.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<style type="text/css">
	th{
		font-size: 24px;
		padding: 10px 14px;
		background: #abc8e5;
	}
	td{
		padding:10px 14px;
		font-size: 20px;
	}
	tr:nth-child(odd){
		background:#ffffff;	
	}
	tr:nth-child(even){
		background:#f1f1f1;	
	}
	tr:nth-child(odd):hover{
		background:#6ccb044d;	
		border:1px solid green;
	}
	tr:nth-child(even):hover{
		background:#6ccb044d;	
		border:1px solid green;
	}
	a{
		text-decoration: none;
		color: green;
		font-weight: bold;
	}
	a:visited{
		text-decoration: none;
		color:green;
		font-weight: bold;
	}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%
	ArrayList<BookBean> bookList = (ArrayList<BookBean>)request.getAttribute("bookList");
	ArrayList<MyBookBean> myBookList = (ArrayList<MyBookBean>)request.getAttribute("myBookList");
	String uname = (String)session.getAttribute("uname");
	String alreadyExist = (String)request.getAttribute("alreadyExist");
%>
</head>
<body align="center" class="row">
<div class="col-lg-6">
	<div align="center" style="font-size: 30px;font-weight:bold;font-family: monospace;color: teal;"> 
		<h2>WELCOME, <%= uname.toUpperCase() %></h2>
	</div>
	<table align="center">
		<tr>	
			<th>Book Name</th>
			<th>Books Available</th>
			<th>Issue</th>
		</tr>
		<%
			for(BookBean b: bookList){
		%>
			<tr align="center">
				<td><%= b.getBookName() %></td>
				<td><%= b.getBookCount() %></td>
				<% if(b.getBookCount() > 0){ %>
				<td><a href="IssueServlet?bookname=<%= b.getBookName()%>">+ Issue</a></td>
				<% }else{ %>
				<td style="color:red;">NOT AVAILABLE</td>
				<% } %>
			</tr>
		<%
			}
		%>
	</table>
</div>
<div class="col-lg-6">
	<div align='center' style="text-align: center !important;font-size: 30px;font-weight:bold;font-family: fantasy;color:orange;">
		<p>YOUR BOOKS</p>
	</div>
	<table align="center">
		<tr>	
			<th>Sr. No</th>
			<th>Book Name</th>
			<th>Revoke</th>
		</tr>
		<%
			if(myBookList != null)
			{
			int count = 0;
			for(MyBookBean b: myBookList){
				if(b.getBookName().trim().length() > 0){
		%>
			<tr align="center">
				<td><%= ++count %></td>
				<td><%= b.getBookName() %></td>
				<td><a style="color:red;font-weight:bold;" href="RevokeServlet?bookname=<%= b.getBookName()%>"> - Revoke</a></td>
			</tr>
		<%
			}
			}
			}
		%>
		<h3 align="center">
		<%= alreadyExist !=null?alreadyExist:""	 %>
	</h3>
	</table>
	<div align="center"><button style="margin-top:20px;widht: 70px;height: 50px;"><a href="SessionLogoutServlet">Logout</a></button></div>
</div>
</body>
</html>