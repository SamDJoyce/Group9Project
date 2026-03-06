<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     
    import="users.Employee"
%>
<%
	Employee e = (Employee) request.getAttribute("employee");
	String status = (String) request.getAttribute("status");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
	<title>Employee Dashboard</title>
</head>

<body>
	<h1>Welcome, <%= e.getFirstName() %></h1>
</body>

</html>