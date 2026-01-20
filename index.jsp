<%
	if(session!=null && session.getAttribute("user_id")!=null){
		String url=response.encodeRedirectURL("home.jsp");
		response.sendRedirect(url);
		return;
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<title>index</title>
	</head>
	<body>
		<h1>index</h1>
		<a href="log_in.jsp">log in</a><br><br>
		<a href="register.jsp">register</a>
	</body>
</html>
