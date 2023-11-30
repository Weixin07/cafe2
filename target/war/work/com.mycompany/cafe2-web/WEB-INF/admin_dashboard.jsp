<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>
<style>
    /* Basic CSS for styling */
    body { font-family: Arial, sans-serif; }
    .dashboard { width: 600px; margin: 0 auto; text-align: center; }
    .dashboard a { display: block; margin: 20px; }
</style>
</head>
<body>
    <div class="dashboard">
        <h1>Admin Dashboard</h1>
        
        <!-- Admin functionalities -->
        <a href="ManageStaff.jsp">Manage Staff</a>
        <a href="view_reports.jsp">View Reports</a>
        <a href="system_settings.jsp">System Settings</a>
        <!-- Add more links or functionalities as needed -->

        <!-- Logout link -->
        <a href="logout.jsp">Logout</a>
    </div>
</body>
</html>
