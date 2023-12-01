package Receipt;

import Customer.Customer;
import SStaff.SStaff;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ViewReceipt", urlPatterns = {"/ViewReceipt"})
public class ViewReceipt extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession s = request.getSession();
        Customer customer = (Customer) s.getAttribute("customer");
        SStaff sstaff = (SStaff) s.getAttribute("sstaff");

        if (sstaff != null) {
            request.setAttribute("hideSMRELabel", false);
            request.setAttribute("hideSAOLabel", false);
            request.setAttribute("hideSEPHLabel", false);
            request.setAttribute("hideEPLabel", false);
            request.setAttribute("hideBVPHLabel", true);
            request.setAttribute("hideAMSLabel", true);
            request.setAttribute("hideLoginLabel", true);
            request.setAttribute("hideBuyButton", true);
            request.setAttribute("uNameLabelText", sstaff.getSsUsername());
            request.getRequestDispatcher("viewSaleRecords.jsp").include(request, response);
        } else if (customer != null) {
            request.setAttribute("hideSMRELabel", true);
            request.setAttribute("hideSAOLabel", true);
            request.setAttribute("hideSEPHLabel", true);
            request.setAttribute("hideEPLabel", false);
            request.setAttribute("hideBVPHLabel", false);
            request.setAttribute("hideAMSLabel", true);
            request.setAttribute("hideLoginLabel", true);
            request.setAttribute("uNameLabelText", customer.getcEmail());
            request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
