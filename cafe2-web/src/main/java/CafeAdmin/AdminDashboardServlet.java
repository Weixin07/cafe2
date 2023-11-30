import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminDashboardServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action from the request
        String action = request.getParameter("action");

        // Based on the action parameter, redirect to the appropriate JSP
        switch (action) {
            case "searchManagingStaff" -> response.sendRedirect("SearchManagingStaff.jsp");
            case "addStaff" -> response.sendRedirect("AddStaff.jsp");
            case "updateManagingStaff" -> response.sendRedirect("UpdateManagingStaff.jsp");
            case "deleteStaff" -> response.sendRedirect("DeleteStaff.jsp");
            case "logout" -> response.sendRedirect("logout.jsp");
            default -> response.sendRedirect("admin_dashboard.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
