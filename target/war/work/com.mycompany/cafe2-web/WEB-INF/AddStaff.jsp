<%@ page import="java.util.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.ejb.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Managing Staff</title>
</head>
<body>

<h2>Add New Managing Staff</h2>
<form action="AddStaffServlet" method="POST">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    Admin ID: <input type="text" name="adminId"><br>
    <input type="submit" value="Add Staff">
</form>

</body>
</html>
