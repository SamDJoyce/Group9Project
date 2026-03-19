<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     
    import="users.Employee"
%>
<%
	HttpSession s = request.getSession(false);
	Employee e = (Employee) request.getAttribute("employee");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
	<title>Employee Dashboard</title>
</head>

<body>
<%
    String status = (String) request.getAttribute("status");
    if (status != null && "loginSuccessful".equalsIgnoreCase(status)){
%>		
		<p style="color:green">Login successful</p>
<%
    }
%>
	<h1>Welcome, <%= e.getFirstName() %></h1>
	<br><br><br>
<%
	if (s != null && s.getAttribute("userId") != null){
%>
		<p>Signed in as <b><%= e.getFirstName() + " " + e.getLastName()%></b> • <a href='logout'>Logout</a></p>
<%
	}
%>
	
</body>

</html>