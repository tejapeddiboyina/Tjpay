<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>send</title>
</head>
<body>
	<form action="Send" method="post">
		accnum:<br>
		<input type=text name="acc_num" /><br> name:<br>
		<input type=text name="name" /><br> amount:<br>
		<input type=text name="amount" /> <input type=submit value="send" />
	</form>
</body>
</html>