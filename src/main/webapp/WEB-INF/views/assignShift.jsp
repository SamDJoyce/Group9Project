<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assign Shift</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>

<div class="dashboard-container form-section">

    <div class="top-bar">
        <div class="dashboard-header">
            <h1>Assign Shift</h1>
            <p class="dashboard-intro">
                Assign employees to available shifts.
            </p>
        </div>

       
    </div>

    <div class="dashboard-section shift-form">
        <form action="#" method="post">

            <div class="form-grid">

                <!-- Employee -->
                <div class="form-group">
                    <label for="employee">Select Employee</label>
                    <select id="employee" name="employee">
                        <option value="">Select employee</option>
                        <option>Emily Carter</option>
                        <option>Michael Brown</option>
                        <option>Sophia Wilson</option>
                        <option>Daniel Lee</option>
                    </select>
                </div>

                <!-- Shift -->
                <div class="form-group">
                    <label for="shift">Select Shift</label>
                    <select id="shift" name="shift">
                        <option value="">Select shift</option>
                        <option>Morning Shift (08:00 - 16:00)</option>
                        <option>Evening Shift (16:00 - 00:00)</option>
                        <option>Night Shift (00:00 - 08:00)</option>
                    </select>
                </div>

            </div>

            <!-- Notes -->
            <div class="form-group full-width">
                <label for="notes">Notes (Optional)</label>
                <textarea id="notes" name="notes" rows="4" placeholder="Add any notes..."></textarea>
            </div>

            <div class="form-actions">
                <input type="submit" value="Assign Shift" class="btn">
                <a href="<%= request.getContextPath() %>/managerDash" class="btn small-btn">Cancel</a>
            </div>

        </form>
    </div>

</div>

</body>
</html>