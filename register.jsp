<% String msg=(String) request.getAttribute("msg"); %>

<!DOCTYPE html>
<html>
	<head>
		<title>register</title>
	</head>
	<body>
		<h1>register</h1>
		<form action="register" method="post">
			<span>username:</span>
			<input name="username" type="text"><br><br>
			<span>password:</span>
			<input name="password" type="password"><br><br>
			<span>repeat password:</span>
			<input name="password_repeat" type="password"><br><br>
			<input type="submit" value="submit">
			<% if(msg!=null){ %>
				<p><%= msg %></p>
			<% } %>
		</form>
		<br>
		<a href="log_in.jsp">log in</a><br><br>
		<a href="index.jsp">index</a>
	</body>
</html>
