<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Staff</title>
    <!-- Include CSS and JS files here -->
</head>
<body>
    <h1>Manage Staff</h1>
    
    <!-- Add Staff Form -->
    <h2>Add Staff</h2>
    <form action="AddStaffServlet" method="POST">
        Username: <input type="text" name="username" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" value="Add Staff">
    </form>

    <!-- Update or Delete Staff Form -->
    <h2>Search, Update, or Delete Staff</h2>
    <form action="SearchStaffServlet" method="GET">
        Search Username: <input type="text" name="searchUsername">
        <input type="submit" value="Search">
    </form>

    <% 
    // Database connection
    Connection conn = null;
    Statement stmt = null;
    try {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby:cafeFish", "", "");
        stmt = conn.createStatement();
        String sql = "SELECT * FROM MANAGINGSTAFF";
        ResultSet rs = stmt.executeQuery(sql);

        // Display staff in a table
        out.println("<table border=1><tr><th>ID</th><th>Username</th><th>Actions</th></tr>");
        while(rs.next()) {
            out.println("<tr><td>" + rs.getString("MANAGINGSTAFFID") + "</td>");
            out.println("<td>" + rs.getString("USERNAME") + "</td>");
            out.println("<td><a href='UpdateStaffServlet?id=" + rs.getString("MANAGINGSTAFFID") + "'>Update</a> | ");
            out.println("<a href='DeleteStaffServlet?id=" + rs.getString("MANAGINGSTAFFID") + "'>Delete</a></td></tr>");
        }
        out.println("</table>");
        rs.close();
    } catch(Exception e) {
        e.printStackTrace();
    } finally {
        try { if(stmt!=null) stmt.close(); } catch(SQLException se2) { }
        try { if(conn!=null) conn.close(); } catch(SQLException se) { se.printStackTrace(); }
    }
    %>
</body>
</html>
