<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>After Login...</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//http 1.1
		response.setHeader("Pragma", "no-cache");//http 1.0
		response.setHeader("Expires", "0");//proxies
		if (session.getAttribute("username") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>
	balance profile send transaction history
	<form action="Balance" method="post">
		<input type=submit value="balance" />
	</form>
	<br>
	<form action="Profile" method="post">
		<input type=submit value="profile" />
	</form>
	<br>
	<a href=Send.jsp><input type=submit value="send"></a>
	<br>
	<br>
	<form action="Transaction" method="post">
		<input type=submit value="transaction" />
	</form>
	<br>
	<form action="Logout" method="post">
		<input type=submit value="logout" />
	</form>

</body>
</html>