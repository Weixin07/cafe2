package CafeAdmin;

import java.io.IOException;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import managingStaff.ManagingStaff;
import managingStaff.ManagingStaffFacade;
import cafeAdmin.CafeAdmin;

@WebServlet(name = "DeleteStaffServlet", urlPatterns = {"/DeleteStaffServlet"})
public class DeleteStaffServlet extends HttpServlet {

    @EJB
    ManagingStaffFacade msf; // Use EJB to interact with ManagingStaff entity

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        CafeAdmin admin = (CafeAdmin) session.getAttribute("admin");
        ManagingStaff staff = new ManagingStaff();

        try {
            String staffUsername = request.getParameter("managingStaffUsername");

            staff.setManagingStaffUsername(staffUsername);
            msf.remove(staff); // Remove staff using EJB

            request.setAttribute("messageColor", "green");
            request.setAttribute("messageLabelText", "Staff Successfully Deleted!");
            request.setAttribute("hideMessageLabel", false);
            request.setAttribute("uNameLabelText", admin.getAdminUsername());
            request.getRequestDispatcher("DeleteStaff.jsp").include(request, response);

        } catch (ServletException | IOException e) {
            request.setAttribute("messageLabelText", "Error Deleting Staff: " + e.getMessage());
            request.setAttribute("hideMessageLabel", false);
            request.setAttribute("uNameLabelText", admin.getAdminUsername());
            request.getRequestDispatcher("DeleteStaff.jsp").include(request, response);
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
        return "DeleteStaffServlet handles the deletion of managing staff members";
    }
}
