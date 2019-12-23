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
	a{
		text-decoration: none;
		color: red;
	}
	a:visited{
		text-decoration: none;
		color:red;
	}
	tr:nth-child(odd):hover{
		background:#6ccb044d;	
		border:1px solid green;
	}
	tr:nth-child(even):hover{
		background:#6ccb044d;	
		border:1px solid green;
	}
	
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%
	ArrayList<BookBean> bookList = (ArrayList<BookBean>)request.getAttribute("bookList");
%>
</head>
<body align="center">
	<div align="center" style="font-size: 30px;font-weight:bold;font-family: monospace;color: teal;"> 
		<h2>WELCOME, ADMIN</h2>
	</div>
	<table align="center">
		<tr>	
			<th>Book Name</th>
			<th>Books Available</th>
			<th>Add More Books</th>
		</tr>
		<%
			for(BookBean b: bookList){
		%>
			<tr align="center">
				<td><%= b.getBookName() %></td>
				<td><%= b.getBookCount() %></td>
				<% if(b.getBookCount() > 0){ %>
				<td><a href="IncreaseBookServlet?bookname=<%= b.getBookName()%>">+1 Add Book</a></td>
				<% }else{ %>
				<td style="color:red;">NOT AVAILABLE</td>
				<% } %>
			</tr>
		<%
			}
		%>
	</table>
	<div align="center"><button style="font-weight:bold;widht: 150px;height: 50px;margin-bottom:20px;margin-top:20px;"><a href="BookAddition.html">Add Another Book</a></button></div>
	<div align="center"><button style="widht: 70px;font-weight:bold;height: 50px;"><a href="index.html">Logout</a></button></div>
</body>
</html>