<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login...</title>
</head>
<body>
	<%
		if (session.getAttribute("username") != null)
			response.sendRedirect("AfterLogin.jsp");
	%>
	<div align="center">
		<form action="Login" method="post">
			<table>
				<tr>
					<td>userId</td>
					<td><input type="text" name="user_id" placeholder="email"></td>
				</tr>
				<tr>
					<td>password</td>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
			<input type="submit" value="login">
		</form>
	</div>

</body>
</html>