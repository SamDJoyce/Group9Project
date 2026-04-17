<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                    <tr>
                        <td>Emily Carter</td>
                        <td>emily.carter@example.com</td>
                        <td>Full-Time</td>
                        <td><span class="status-badge">Active</span></td>
                        <td>
                            <a href="#" class="btn small-btn">Edit</a>
                            <a href="#" class="btn small-btn logout">Remove</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Michael Brown</td>
                        <td>michael.brown@example.com</td>
                        <td>Part-Time</td>
                        <td><span class="status-badge">Active</span></td>
                        <td>
                            <a href="#" class="btn small-btn">Edit</a>
                            <a href="#" class="btn small-btn logout">Remove</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Sophia Wilson</td>
                        <td>sophia.wilson@example.com</td>
                        <td>Casual</td>
                        <td><span class="status-badge">Active</span></td>
                        <td>
                            <a href="#" class="btn small-btn">Edit</a>
                            <a href="#" class="btn small-btn logout">Remove</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Daniel Lee</td>
                        <td>daniel.lee@example.com</td>
                        <td>Full-Time</td>
                        <td><span class="status-badge">Active</span></td>
                        <td>
                            <a href="#" class="btn small-btn">Edit</a>
                            <a href="#" class="btn small-btn logout">Remove</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

    </div>

</body>
</html>