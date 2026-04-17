<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="users.User"
%>
<%
    HttpSession s = request.getSession(false);
    User manager = (User) request.getAttribute("manager");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Dashboard</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>

<body>
<div class="dashboard-container">

    <div class="top-bar">
        <h1>Manager Dashboard</h1>

        <% if (s != null && s.getAttribute("userId") != null && manager != null) { %>
            <div class="user-info">
                <span class="user-name"><%= manager.getFirstName() %> <%= manager.getLastName() %></span>
                <a href="<%= request.getContextPath() %>/logout" class="btn logout">Logout</a>
            </div>
        <% } %>
    </div>

    <p class="dashboard-intro">
        Manage employees, shifts, and scheduling tasks from one central workspace.
    </p>

    <h2 class="dashboard-subtitle">
        Welcome, <%= manager != null ? manager.getFirstName() : "Manager" %>
    </h2>

    <div class="dashboard-grid">

        <div class="dashboard-section">
            <h3>Employee Management</h3>
            <p class="section-text">Access employee records and organize workforce information.</p>

            <div class="section-actions">
                <a href="<%= request.getContextPath() %>/addEmployee" class="btn small-btn">Add Employee</a>
                <a href="<%= request.getContextPath() %>/viewEmployees" class="btn small-btn">View Employees</a>
            </div>

            <ul>
                <li>Create and manage employee accounts</li>
                <li>Review employee details</li>
                <li>Track scheduling availability</li>
            </ul>
        </div>

        <div class="dashboard-section">
            <h3>Shift Management</h3>
            <p class="section-text">Plan, assign, and review work shifts efficiently.</p>

            <div class="section-actions">
                <a href="<%= request.getContextPath() %>/newShift" class="btn small-btn">Create Shift</a>
                <a href="<%= request.getContextPath() %>/assignShift" class="btn small-btn">Assign Shift</a>
                <a href="<%= request.getContextPath() %>/viewSchedule" class="btn small-btn">View Schedule</a>
            </div>

            <ul>
                <li>Create new work shifts</li>
                <li>Assign employees to shifts</li>
                <li>Review shift schedules and coverage</li>
            </ul>
        </div>

    </div>
</div>

<%
String successMessage = (String) session.getAttribute("successMessage");
if (successMessage != null) {
%>
<script>
    alert("<%= successMessage %>");
</script>
<%
    session.removeAttribute("successMessage");
}
%>

</body>
</html>