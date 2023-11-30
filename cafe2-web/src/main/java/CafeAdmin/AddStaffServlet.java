package CafeAdmin;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import managingStaff.ManagingStaff;
import managingStaff.ManagingStaffFacade;

@WebServlet(name = "AddStaffServlet", urlPatterns = {"/AddStaffServlet"})
public class AddStaffServlet extends HttpServlet {

    @EJB
    ManagingStaffFacade msf; // Use EJB to interact with ManagingStaff entity

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String adminID = request.getParameter("adminID");

        try (PrintWriter out = response.getWriter()) {
            try {
                if (password.length() < 8 || password.length() > 16) {
                    request.setAttribute("messageLabelText", "Password must be between 8 and 16 characters.");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("addStaff.jsp").include(request, response);
                } else {
                    msf.create(new ManagingStaff(username, password, adminID));

                    String messageColor = "green";
                    request.setAttribute("messageColor", messageColor);
                    request.setAttribute("messageLabelText", "Staff successfully added!");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("adminPanel.jsp").include(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("messageLabelText", "Error in registration. Please try again.");
                request.setAttribute("hideMessageLabel", false);
                request.getRequestDispatcher("addStaff.jsp").include(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "AddStaffServlet handles adding new managing staff members";
    }
}
