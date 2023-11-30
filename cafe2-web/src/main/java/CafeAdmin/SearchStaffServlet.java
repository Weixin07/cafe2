package CafeAdmin;

import cafeAdmin.CafeAdmin;
import managingStaff.ManagingStaff;
import managingStaff.ManagingStaffFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchStaffServlet", urlPatterns = {"/SearchStaffServlet"})
public class SearchStaffServlet extends HttpServlet {

    @EJB
    ManagingStaffFacade msf; // Assuming ManagingStaffFacade is similar to BuyerFacade

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            CafeAdmin admin = (CafeAdmin) session.getAttribute("admin");
            String searchQuery = request.getParameter("searchStaff");

            try {
                List<ManagingStaff> staffList = msf.findAll(); // Retrieve all managing staff
                List<ManagingStaff> filteredStaff = new ArrayList<>();
                boolean found = false;

                for (ManagingStaff staff : staffList) {
                    if (staff.getManagingStaffUsername().toLowerCase().contains(searchQuery.toLowerCase())) {
                        filteredStaff.add(staff);
                        found = true;
                    }
                }

                if (!found) {
                    request.setAttribute("messageLabelText", "No Staff Found, Please Try Again!");
                    request.setAttribute("hideMessageLabel", false);
                } else {
                    request.setAttribute("filteredStaff", filteredStaff);
                    request.setAttribute("hideStaffTableLabel", true);
                }

                request.setAttribute("uNameLabelText", admin.getAdminUsername());
                request.getRequestDispatcher("SearchStaff.jsp").forward(request, response);

            } catch (ServletException | IOException e) {
                request.setAttribute("messageLabelText", "Error fetching Staff, Please Try Again! " + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", admin.getAdminUsername());
                request.setAttribute("hideStaffTableLabel", true);
                request.getRequestDispatcher("SearchStaff.jsp").include(request, response);
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
        return "Servlet for Searching Managing Staff";
    }
}
