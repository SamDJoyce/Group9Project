<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   
    import="users.Manager"
%>
<%
	HttpSession s = request.getSession(false);
	Manager m = (Manager) s.getAttribute("manager");
%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Manager Dashboard</title>
</head>

<body>
	<h1>Welcome, <%= m.getFirstName()%> </h1>
	<h2>Dashboard</h2>
	
</body>

</html>