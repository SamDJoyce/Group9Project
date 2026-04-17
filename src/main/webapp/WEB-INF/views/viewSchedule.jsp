<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Schedule</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>

    <div class="dashboard-container form-section">

        <div class="top-bar">
            <div class="dashboard-header">
                <h1>View Schedule</h1>
                <p class="dashboard-intro">
                    Review assigned shifts and current schedule details.
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
                        <th>Date</th>
                        <th>Shift</th>
                        <th>Assigned Employee</th>
                        <th>Employee Type</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>April 18, 2026</td>
                        <td>Morning Shift (08:00 - 16:00)</td>
                        <td>Emily Carter</td>
                        <td>Full-Time</td>
                        <td><span class="status-badge">Assigned</span></td>
                    </tr>
                    <tr>
                        <td>April 18, 2026</td>
                        <td>Evening Shift (16:00 - 00:00)</td>
                        <td>Michael Brown</td>
                        <td>Part-Time</td>
                        <td><span class="status-badge">Assigned</span></td>
                    </tr>
                    <tr>
                        <td>April 19, 2026</td>
                        <td>Morning Shift (08:00 - 16:00)</td>
                        <td>Sophia Wilson</td>
                        <td>Casual</td>
                        <td><span class="status-badge">Assigned</span></td>
                    </tr>
                    <tr>
                        <td>April 19, 2026</td>
                        <td>Night Shift (00:00 - 08:00)</td>
                        <td>Daniel Lee</td>
                        <td>Full-Time</td>
                        <td><span class="status-badge">Assigned</span></td>
                    </tr>
                </tbody>
            </table>
        </div>

    </div>

</body>
</html>