<%@ page import="java.util.*, java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Managing Staff</title>
</head>
<body>
    <h2>Delete Managing Staff</h2>
    <form action="DeleteStaffServlet" method="post">
        <label for="managingStaffUsername">Select Managing Staff:</label>
        <select name="managingStaffUsername" id="managingStaffUsername">
            <% 
                Connection conn = null;
                Statement stmt = null;
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                    conn = DriverManager.getConnection("jdbc:derby:cafeFish", "", "");
                    stmt = conn.createStatement();
                    String sql = "SELECT managingStaffUsername FROM ManagingStaff";
                    ResultSet rs = stmt.executeQuery(sql);
                    while(rs.next()) {
                        String username = rs.getString("managingStaffUsername");
                        out.print("<option value='" + username + "'>" + username + "</option>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(stmt != null) stmt.close();
                    if(conn != null) conn.close();
                }
            %>
        </select>
        <input type="submit" value="Delete">
    </form>
</body>
</html>
