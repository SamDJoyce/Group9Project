<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>Account Login</title>
</head>

<body>
	<h2>ShiftManager — Login</h2>

<%
    String error = (String) request.getAttribute("loginError");
    if (error != null) {
%>
    <p style="color:red">Login error: <%= error %></p>
<%
    }
%>

<form method="post" action="<%= request.getContextPath() %>/login">
    Email: <input name="email"><br>
    Password: <input name="password" type="password"><br>
    <button type="submit">Sign in</button>
</form>
<p>Don't have an account? <a href="<%= request.getContextPath() %>/NewUser">Register here</a></p>
</body>

</html>