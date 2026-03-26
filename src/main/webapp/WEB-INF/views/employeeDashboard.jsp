<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="users.Employee"
%>
<%
    HttpSession s = request.getSession(false);
    Employee e = (Employee) request.getAttribute("employee");
    String status = (String) request.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>

<div class="dashboard-container">

    <div class="top-bar">
        <div>
            <h1>Employee Dashboard</h1>
            <p class="dashboard-intro">
                View your profile information, assigned shifts, and announcements.
            </p>
        </div>

        <% if (s != null && s.getAttribute("userId") != null && e != null) { %>
            <div class="user-info">
                <span class="user-name"><%= e.getFirstName() %> <%= e.getLastName() %></span>
                <a href="logout" class="btn small-btn logout">Logout</a>
            </div>
        <% } %>
    </div>

    <% if (status != null && "loginSuccessful".equalsIgnoreCase(status)) { %>
        <div class="statusMessage">
            <h2 style="color: green;">Login successful</h2>
        </div>
    <% } %>

    <div class="dashboard-grid">

        <div class="dashboard-section">
            <h3>My Profile</h3>
            <p class="section-text">Your basic account information.</p>
            <ul class="profile-list">
                <li><strong>First Name:</strong> <%= e != null ? e.getFirstName() : "" %></li>
                <li><strong>Last Name:</strong> <%= e != null ? e.getLastName() : "" %></li>
                <li><strong>Role:</strong> Employee</li>
                <li><strong>Status:</strong> Active</li>
            </ul>
        </div>

        <div class="dashboard-section">
            <h3>My Upcoming Shifts</h3>
            <p class="section-text">Your assigned work schedule will appear here.</p>

            <table class="dashboard-table">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Day</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="5" class="empty-cell">No shifts available yet.</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="dashboard-section">
            <h3>Announcements</h3>
            <p class="section-text">Important updates from management will appear here.</p>
            <ul class="announcement-list">
                <li>No new announcements.</li>
            </ul>
        </div>

        <div class="dashboard-section">
            <h3>Quick Actions</h3>
            <p class="section-text">
                Additional employee self-service features can be added here in future iterations.
            </p>
        </div>

    </div>

</div>

</body>
</html>