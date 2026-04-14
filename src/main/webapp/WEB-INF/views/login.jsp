<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
	<title>Account Login</title>
</head>

<body>
	<h2>ShiftManager — Login</h2><br>
<%
    // Status messages
    String status = (String) request.getAttribute("status");
    if (status != null){
    	if ("successfullCreation".equalsIgnoreCase(status)){
%>
			<p style="color:green">Account created successfully!</p>
<%
    	} else if ("failedCreation".equalsIgnoreCase(status)){
%>
			<p style="color:red">Account creation failed.</p>
<%    		
    	} else if ("loginError".equalsIgnoreCase(status)) {
%>
    		<p style="color:red">Login error.</p>
<%
    	}
    	
    }
%>


<form method="post" action="<%= request.getContextPath() %>/login">
    <label for="email">Email:</label>
    <input name="email" type="email" id="email"><br>
    <label for="password">Password:</label>
    <input name="password" id="password" type="password"><br>
    <input type="submit" id="signInButton" value="Sign In">
</form><br>
<p>Don't have an account? <a href="<%= request.getContextPath() %>/newUser">Register here</a></p>
</body>

</html>