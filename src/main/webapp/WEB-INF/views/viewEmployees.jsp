<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List"
	import="users.Employee"
%>	
<% 
	List<Employee> employees = (List<Employee>)request.getAttribute("employees");    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Employees</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>

    <div class="dashboard-container form-section">

        <div class="top-bar">
            <div class="dashboard-header">
                <h1>View Employees</h1>
                <p class="dashboard-intro">
                    Review employee records and workforce details.
                </p>
            </div>

            <div class="user-info">
                <a href="<%= request.getContextPath() %>/managerDash" class="btn small-btn">Back</a>
            </div>
        </div>

        <div class="dashboard-section">
            <table class="dashboard-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Employee Type</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <%
                	for (Employee empl : employees) {
                %>
                	<tr>
                		<td><%= empl.getFullName() %></td>
                		<td><%= empl.getEmail() %></td>
                		<td><%= empl.getType() %></td>
                		<td><span class="status-badge">Active</span></td>
                		<td><a href="#" class="btn small-btn">Edit</a>
                            <a href="#" class="btn small-btn logout">Remove</a>
                        </td>
                	</tr>
                <%
                	}
                %>
                </tbody>
            </table>
        </div>

    </div>

</body>
</html>