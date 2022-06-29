<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!-- Used for  -->   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- Used for loops --> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
</head>
<body>
	<h2>Student Profile Edit</h2>
	
	<form:form action="/Maven_Demo/update" method="post">
		<form:hidden path="id"/><br><br>
		<form:input path="fname"/><br><br>
		<form:input path="lname"/><br><br>
		<form:input path="email"/><br><br>
		<form:input path="password"/><br><br>
		<button type="submit">Update</button>
	</form:form>
	
</body>
</html>