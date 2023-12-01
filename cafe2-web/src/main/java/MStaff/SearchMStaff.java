package MStaff;

import sAdmin.SAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SearchMStaff", urlPatterns = {"/SearchMStaff"})
public class SearchMStaff extends HttpServlet {

    @EJB
    MStaffFacade msf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession s = request.getSession();
            SAdmin admin = (SAdmin) s.getAttribute("admin");
            String searchMStaff = request.getParameter("searchMStaff");

            try {

                List<MStaff> mStaffList = msf.findAll();
                List<MStaff> filteredMStaff = new ArrayList<>();
                boolean msFound = false;

                for (MStaff ms : mStaffList) {
                    if (ms.getMsUsername().toLowerCase().contains(searchMStaff.toLowerCase())) {
                        filteredMStaff.add(ms);
                        msFound = true;
                    }
                }

                if (msFound == false) {
                    request.setAttribute("messageLabelText", "Managing staff do not exist.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", admin.getaUsername());
                    request.getRequestDispatcher("SearchMStaff.jsp").include(request, response);
                } else {

                    request.setAttribute("filteredMStaff", filteredMStaff);
                    request.setAttribute("uNameLabelText", (admin.getaUsername()));
                    request.setAttribute("hideBuyerTableLabel", true);
                }

                request.getRequestDispatcher("SearchMStaff.jsp").forward(request, response);
            } catch (ServletException | IOException e) {

                request.setAttribute("messageLabelText", "Error. Please try again." + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", admin.getaUsername());
                request.setAttribute("hideBuyerTableLabel", true);

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
        return "Short description";
    }

}
