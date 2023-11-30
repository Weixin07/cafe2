<%@ page import="java.util.*, managingStaff.*" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Managing Staff</title>
</head>
<body>
    <h2>Update Managing Staff</h2>
    <form action="UpdateManagingStaffServlet" method="POST">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">New Password:</label>
        <input type="password" id="password" name="password">
        <br>
        <label for="adminId">Admin ID:</label>
        <input type="text" id="adminId" name="adminId">
        <br>
        <input type="submit" value="Update">
    </form>
</body>
</html>
