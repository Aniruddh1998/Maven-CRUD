<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cookies</title>
</head>
<body>
	<h2>Cookies Set</h2>
	<%
		Cookie[] arr = request.getCookies();
		
		for(Cookie c:arr){
			out.println(c.getName()+" "+c.getValue());
		}
	%>
</body>
</html>