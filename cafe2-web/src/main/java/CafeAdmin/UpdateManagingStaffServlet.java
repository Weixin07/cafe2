package CafeAdmin;

import cafeAdmin.CafeAdmin;
import managingStaff.ManagingStaff;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import managingStaff.ManagingStaffFacade;

@WebServlet(name = "UpdateManagingStaff", urlPatterns = {"/UpdateManagingStaff"})
public class UpdateManagingStaffServlet extends HttpServlet {

    @EJB
    ManagingStaffFacade msf; // Use EJB to interact with ManagingStaff entitycade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            CafeAdmin admin = (CafeAdmin) session.getAttribute("admin");
            ManagingStaff staff = new ManagingStaff();

            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String adminId = request.getParameter("adminId");

                if (password.length() < 8 || password.length() > 16) {
                    request.setAttribute("messageLabelText", "Password must be between 8 and 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("adminNameLabelText", admin.getAdminID());
                    request.getRequestDispatcher("updateManagingStaff.jsp").include(request, response);
                } else {
                    staff.setManagingStaffUsername(username);
                    staff.setManagingStaffPassword(password);
                    staff.setAdminID(adminId);
                    msf.edit(staff); // Assuming edit method is implemented in EJB

                    String messageColor = "green";
                    request.setAttribute("messageColor", messageColor);
                    request.setAttribute("messageLabelText", "Managing Staff Info Successfully Edited!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("adminNameLabelText", admin.getAdminID());
                    request.getRequestDispatcher("UpdateManagingStaff.jsp").include(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("messageLabelText", "Invalid Managing Staff Details, Please Try Again! " + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("adminNameLabelText", admin.getAdminID());
                request.getRequestDispatcher("UpdateManagingStaff.jsp").include(request, response);
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
        return "Updates managing staff details";
    }
}
