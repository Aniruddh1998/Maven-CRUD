<html>
<head>
	<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>Hello World!</h2>
	<!-- 
	<form action="addition" method="post">
		<input type="text" name="num1" placeholder="Number 1"><br><br>
		<input type="text" name="num2" placeholder="Number 2"><br><br>
		
		<button type="submit">Submit</button>
	</form>
	 -->
	 <form action="save" method="post">
	 	<input type="text" name="fname" placeholder="First name"><br><br>
	 	<input type="text" name="lname" placeholder="Last name"><br><br>
	 	<input type="email" name="email" placeholder="Email"><br><br>
	 	<input type="password" name="password" placeholder="Password"><br><br>
	 	
	 	<button type="submit">Submit</button>
	 </form>
</body>
</html>
