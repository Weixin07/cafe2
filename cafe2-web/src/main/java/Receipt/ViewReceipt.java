/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Receipt;

import sAdmin.SAdmin;
import SStaff.SStaff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author pc
 */
@WebServlet(name = "ViewReceipt", urlPatterns = {"/ViewReceipt"})
public class ViewReceipt extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession s = request.getSession();
        SAdmin buyer = (SAdmin)s.getAttribute("user");
        SStaff seller = (SStaff)s.getAttribute("seller");
        
            if (seller!=null){
                request.setAttribute("hideSMRELabel", false);
                request.setAttribute("hideSAOLabel", false);
                request.setAttribute("hideSEPHLabel", false);
                request.setAttribute("hideEPLabel", false);
                request.setAttribute("hideBVPHLabel", true);
                request.setAttribute("hideAMSLabel", true);
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("hideBuyButton", true);
                request.setAttribute("uNameLabelText", seller.getsName());
                request.getRequestDispatcher("viewSaleRecords.jsp").include(request, response);
            }else if (buyer!=null){
                request.setAttribute("hideSMRELabel", true);
                request.setAttribute("hideSAOLabel", true);
                request.setAttribute("hideSEPHLabel", true);
                request.setAttribute("hideEPLabel", false);
                request.setAttribute("hideBVPHLabel", false);
                request.setAttribute("hideAMSLabel", true);
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("uNameLabelText", buyer.getbUsername());
                request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
            }
        
        
        try (PrintWriter out = response.getWriter()) {
           
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
