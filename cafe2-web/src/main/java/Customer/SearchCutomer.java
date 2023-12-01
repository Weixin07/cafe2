package Customer;

import SStaff.SStaff;
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

@WebServlet(name = "SearchCutomer", urlPatterns = {"/SearchCutomer"})
public class SearchCutomer extends HttpServlet {

    @EJB
    CustomerFacade cf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession s = request.getSession();
            SStaff sstaff = (SStaff) s.getAttribute("smstaff");
            String searchCustomer = request.getParameter("searchCustomer");

            try {

                List<Customer> CustomerList = cf.findAll();
                List<Customer> filteredCustomer = new ArrayList<>();
                boolean cFound = false;

                for (Customer c : CustomerList) {
                    if (c.getcEmail().toLowerCase().contains(searchCustomer.toLowerCase())) {
                        filteredCustomer.add(c);
                        cFound = true;
                    }
                }

                if (cFound == false) {
                    request.setAttribute("messageLabelText", "Stall staff do not exist.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                    request.getRequestDispatcher("SearchMStaff.jsp").include(request, response);
                } else {

                    request.setAttribute("filteredSStaff", filteredCustomer);
                    request.setAttribute("uNameLabelText", (sstaff.getSsUsername()));
                    request.setAttribute("hideBuyerTableLabel", true);
                }

                request.getRequestDispatcher("SearchCutomer.jsp").forward(request, response);
            } catch (ServletException | IOException e) {

                request.setAttribute("messageLabelText", "Error. Please try again." + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                request.setAttribute("hideMStaffTableLabel", true);

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
