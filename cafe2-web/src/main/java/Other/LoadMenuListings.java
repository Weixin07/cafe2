/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import Customer.Customer;
import Menu.Menu;
import Menu.MenuFacade;
import SStaff.SStaff;
import MStaff.MStaff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sAdmin.SAdmin;

@WebServlet(name = "LoadMenuListings", urlPatterns = {"/LoadMenuListings"})
public class LoadMenuListings extends HttpServlet {

    @EJB
    private MenuFacade mf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession s = request.getSession();
            SStaff sstaff = (SStaff) s.getAttribute("sstaff");
            Customer customer = (Customer) s.getAttribute("customer");
            MStaff mstaff = (MStaff) s.getAttribute("mstaff");
            SAdmin admin = (SAdmin) s.getAttribute("admin");

            if (customer != null) {
                request.setAttribute("hideSMRELabel", true);
                request.setAttribute("hideSAOLabel", true);
                request.setAttribute("hideSEPHLabel", true);
                request.setAttribute("hideEPLabel", false);
                request.setAttribute("hideBVPHLabel", false);
                request.setAttribute("hideSMBLabel", true);
                request.setAttribute("hideSMSLabel", true);
                request.setAttribute("hideSFBALabel", true);
                request.setAttribute("hideAMSLabel", true);
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("uNameLabelText", customer.getcEmail());
            } else if (sstaff != null) {
                request.setAttribute("hideSMRELabel", false);
                request.setAttribute("hideSAOLabel", false);
                request.setAttribute("hideSEPHLabel", false);
                request.setAttribute("hideEPLabel", false);
                request.setAttribute("hideBVPHLabel", true);
                request.setAttribute("hideSMBLabel", true);
                request.setAttribute("hideSMSLabel", true);
                request.setAttribute("hideSFBALabel", true);
                request.setAttribute("hideAMSLabel", true);
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("hideBuyButton", true);
                request.setAttribute("uNameLabelText", sstaff.getSsUsername());
            } else if (mstaff != null) {
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("hideSAOLabel", true);
                request.setAttribute("hideEPLabel", false);
                request.setAttribute("hideSMRELabel", true);
                request.setAttribute("hideSEPHLabel", true);
                request.setAttribute("hideBVPHLabel", true);
                request.setAttribute("hideSMBLabel", false);
                request.setAttribute("hideSMSLabel", false);
                request.setAttribute("hideSFBALabel", false);
                request.setAttribute("hideAMSLabel", true);
                request.setAttribute("hideBuyButton", true);
                request.setAttribute("uNameLabelText", mstaff.getMsUsername());
            } else if (admin != null) {
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("hideSAOLabel", true);
                request.setAttribute("hideEPLabel", true);
                request.setAttribute("hideSMRELabel", true);
                request.setAttribute("hideSEPHLabel", true);
                request.setAttribute("hideBVPHLabel", true);
                request.setAttribute("hideSMBLabel", true);
                request.setAttribute("hideSMSLabel", true);
                request.setAttribute("hideSFBALabel", true);
                request.setAttribute("hideAMSLabel", false);
                request.setAttribute("hideBuyButton", true);
                request.setAttribute("uNameLabelText", admin.getaUsername());
            } else {
                request.setAttribute("hideBuyButton", true);
            }

            List<Menu> menu = mf.findAll();
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("menuListings.jsp").include(request, response);
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
