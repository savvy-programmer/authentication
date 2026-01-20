<% String msg=(String) request.getAttribute("msg"); %>

<!DOCTYPE html>
<html>
	<head>
		<title>log in</title>
	</head>
	<body>
		<h1>log in</h1>
		<form action="log_in" method="post">
			<span>username:</span>
			<input name="username" type="text"><br><br>
			<span>password:</span>
			<input name="password" type="password"><br><br>
			<input type="submit" value="submit">
			<% if(msg!=null){ %>
				<p><%= msg %></p>
			<% } %>
		</form>
		<br>
		<a href="register.jsp">register</a><br><br>
		<a href="index.jsp">index</a>
	</body>
</html>
