<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Employee</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
	<div class= "dashboard-container form-section">
		
		<div class= "top-bar">
			<div class="dashboard-header">
				<h1>Add Employee</h1>
				<p class="dashboard-intro">
					Enter employee information to create a new employee account.
				</p>
			</div>
			
			
		</div>
		
		<div class="dashboard-section shift-form">
			<form action="#" method="post">
				<div class="form-grid">
					<div class="form-group">
						<label for="firstName">First Name</label>
						<input type="text" id="firstName" name="firstName" placeholder="Enter first name">
					</div>
					
					<div class="form-group">
						<label for="lastName">Last Name</label>
						<input type="text" id="lastName" name="lastName" placeholder="Enter last name">
					</div>
					
					<div class="form-group">
						<label for="email">Email Address</label>
						<input type="email" id="email" name="email" placeholder="Enter email address">
					</div>
					
					<div class="form-group">
						<label for="type">Employee Type</label>
						<select id="type" name="type">
							<option value="">Select employee type</option>
							<option value="fulltime">Full-Time</option>
							<option value="parttime">Part-Time</option>
							<option value="casual">Casual</option>
						</select>
					</div>
					
					<div class="form-group">
						<label for="password"> Temporary Password</label>
						<input type="password" id="password" name="password" placeholder="Enter temporary password">
					</div>
					
					<div class="form-group">
						<label for="confirmpass"> Confirm Temporary Password</label>
						<input type="password" id="confirmpass" name="confirmpass" placeholder="Re-enter password">
					</div>
				</div>
				
				<div class="form-actions">
					<input type="submit" value="Save Employee" class="btn">
					<a href="<%= request.getContextPath() %>/managerDash" class="btn small-btn">Cancel</a>
				</div>
				
			</form>
		</div>
	</div>

</body>
</html>