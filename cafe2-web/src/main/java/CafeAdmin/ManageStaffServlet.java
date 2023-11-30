package CafeAdmin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ManageStaffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        switch (action) {
            case "add" -> request.getRequestDispatcher("/AddStaff.jsp").forward(request, response);
            case "delete" -> request.getRequestDispatcher("/DeleteStaff.jsp").forward(request, response);
            case "search" -> request.getRequestDispatcher("/SearchStaff.jsp").forward(request, response);
            case "update" -> request.getRequestDispatcher("/UpdateStaff.jsp").forward(request, response);
            default -> request.getRequestDispatcher("/admin_dashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
