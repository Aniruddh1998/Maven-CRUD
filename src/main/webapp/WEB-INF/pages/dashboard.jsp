<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	HttpSession sess = request.getSession(false);
	String email=(String)sess.getAttribute("username");
	out.println(email);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Dashboard</h2>
	
	<a href="logout">Logout</a>
</body>
</html>