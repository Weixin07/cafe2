<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Dashboard</title>
        <style>
            /* Basic CSS for styling */
            body {
                font-family: Arial, sans-serif;
            }
            .dashboard {
                width: 600px;
                margin: 0 auto;
                text-align: center;
            }
            .dashboard a {
                display: block;
                margin: 20px;
            }
        </style>
    </head>
    <body>
        <div class="dashboard">
            <h1>Admin Dashboard</h1>

            <!-- Admin functionalities -->
            <a href="SearchStaff.jsp">Search Managing Staff</a>
            <a href="AddStaff.jsp">Add Managing Staff</a>
            <a href="UpdateManagingStaff.jsp">Update Managing Staff</a>
            <a href="DeleteStaff.jsp">Delete Managing Staff</a>

            <!-- Logout link -->
            <a href="logout.jsp">Logout</a>
        </div>
    </body>
</html>
