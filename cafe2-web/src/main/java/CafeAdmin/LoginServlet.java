import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Database connection setup
            String url = "jdbc:derby:cafeFish";
            String dbUsername = ""; 
            String dbPassword = ""; 

            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM ADMIN WHERE USERNAME=? AND PASSWORD=?");
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Valid login, redirect to admin dashboard
                response.sendRedirect("adminDashboard.jsp");
            } else {
                // Invalid login, redirect back to login page with an error message
                response.sendRedirect("login.jsp?error=Invalid credentials");
            }

            // Clean up
            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Database error");
        }
    }
}
