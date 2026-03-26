<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="users.User"
%>
<%
    HttpSession s = request.getSession(false);
    User manager = (User) request.getAttribute("manager");
    String status = (String) request.getAttribute("status");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Shift</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>

<body>
<div class="dashboard-container">

    <div class="top-bar">
        <div class="dashboard-header">
            <h1>Create New Shift</h1>
            <p class="dashboard-intro">
                Enter the shift details below to create a new work shift.
            </p>
        </div>

        <% if (s != null && s.getAttribute("userId") != null && manager != null) { %>
            <div class="user-info">
                <span class="user-name"><%= manager.getFirstName() %> <%= manager.getLastName() %></span>
                <a href="<%= request.getContextPath() %>/logout" class="btn logout">Logout</a>
            </div>
        <% } %>
    </div>

    <% if (status != null) { %>
        <div class="statusMessage">
            <h2><%= status %></h2>
        </div>
    <% } %>

    <div class="dashboard-section form-section">
        <h3>Shift Details</h3>
        <p class="section-text">Fill out the form to add a new shift to the schedule.</p>

        <form action="<%= request.getContextPath() %>/createShift" method="post" class="shift-form">
            <div class="form-grid">
                <div class="form-group">
                    <label for="shiftDate">Shift Date</label>
                    <input type="date" id="shiftDate" name="shiftDate" required>
                </div>

                <div class="form-group">
                    <label for="employeeType">Employee Type</label>
                    <select id="employeeType" name="employeeType" required>
                        <option value="">Select type</option>
                        <option value="fullTime">Full-Time</option>
                        <option value="partTime">Part-Time</option>
                        <option value="casual">Casual</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="startTime">Start Time</label>
                    <input type="time" id="startTime" name="startTime" required>
                </div>

                <div class="form-group">
                    <label for="endTime">End Time</label>
                    <input type="time" id="endTime" name="endTime" required>
                </div>

                <div class="form-group">
                    <label for="requiredEmployees">Required Employees</label>
                    <input type="number" id="requiredEmployees" name="requiredEmployees" min="1" placeholder="Enter number" required>
                </div>
            </div>

            <div class="form-group full-width">
                <label for="notes">Notes</label>
                <textarea id="notes" name="notes" rows="4" placeholder="Optional notes or instructions"></textarea>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn">Create Shift</button>
                <a href="<%= request.getContextPath() %>/managerDash" class="btn small-btn">Cancel</a>
            </div>
        </form>
    </div>

</div>
</body>
</html>