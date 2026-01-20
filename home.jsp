<%
	if(session==null || session.getAttribute("user_id")==null){
		response.sendRedirect("log_in.jsp");
		return;
	}
	
	String username=(String) session.getAttribute("username");
%>

<!DOCTYPE html>
<html>
	<head>
		<title>home</title>
	</head>
	<body>
		<h1>home</h1>
		<p>hello <%= username %>!</p>
		<a href="log_out">log out</a>
	</body>
</html>
