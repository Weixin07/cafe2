<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <!-- Add any required CSS references here -->
</head>
<body>
    <h2>Admin Login</h2>
    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
