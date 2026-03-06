<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String status = (String)request.getAttribute("status");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
	<title>Register New User</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/login">Back to Login page</a><br>
<h1>Register New User</h1>
	<%
	if (status != null) {
	%>
		<div id="newUserStatus" class="statusMessage" >
		<h2><%= status %></h2><br><br>
		</div>
	<%
	}
	%>
	<div id="newUserForm"><form action="<%= request.getContextPath() %>/newUser" method="post">
		<!-- First Name -->
		<label for="firstName">First Name: </label>
		<input id="firstName" name="firstName" type="text" required> <br>
		<!-- Last Name -->
		<label for="lastName">Last Name: </label>
		<input id="lastName" name="lastName" type="text" required> <br>
		<!-- Email -->
		<label for="email">Email: </label>
		<input id="email" name="email" type="text" required> <br>
		<!-- Type -->
		<p>Employee Type: </p>
		
		<input type='radio' name='type' id='fullTime' value='fullTime' required>
		<label for='fullTime'>Full time</label><br>
		<input type='radio' name='type' id='partTime' value='partTime' required>
		<label for='partTime'>Part time</label><br>
		<input type='radio' name='type' id='casual' value='casual' required>
		<label for='casual'>Casual</label><br>
		<input type='radio' name='type' id='manager' value='manager' required>
		<label for='manager'>Manager</label><br><br>
		<!-- Password -->
		<label for="password">Enter password: </label>
		<input type="password" id="password" name="password" minlength="8" required><br>
		<label for="confirmPass">Confirm password: </label>
		<input type="password" id="confirmPass" name="confirmPass" minlength="8" required><br><br>
		<input type="hidden" name="action" value="createUser">
		<input type="submit">
	</form></div>
</body>

</html>