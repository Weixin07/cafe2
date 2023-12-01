/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Receipt;

import sAdmin.SAdmin;
import sOrder.SOrder;
import sOrder.SOrderFacade;
import Menu.Menu;
import Menu.MenuFacade;
import SStaff.SStaff;
import MStaff.MStaff;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import jakarta.ejb.EJB;
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
@WebServlet(name = "LoadReceiptTable", urlPatterns = {"/LoadReceiptTable"})
public class LoadReceiptTable extends HttpServlet {
    
    @EJB 
    ReceiptFacade rf;
    @EJB
    SOrderFacade of;
    @EJB
    MenuFacade pf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        SStaff seller = (SStaff)s.getAttribute("seller");
        SAdmin buyer = (SAdmin)s.getAttribute("user");
        MStaff staff = (MStaff)s.getAttribute("staff");
        
        List<Receipt> receipt = rf.findAll();
        List<SOrder> offer = of.findAll();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        
        
        try (PrintWriter out = response.getWriter()) {
          if (buyer != null){
          for (Receipt rec : receipt) {
              for(SOrder off : offer){
                    if (rec.getoId() == off.getoId() && buyer.getbUsername().equals(off.getbEmail())){ 
                     out.println("<tr>");
                     out.println("<td>" + rec.getrId() + "</td>");
                     out.println("<td>" + rec.getrFeedback() + "</td>");
                     out.println("<td>" + rec.getrRating() + "</td>");
                     out.println("<td>" + sdf.format(rec.getrDateOfSale()) + "</td>");
                     out.println("<td>" + rec.getoId() + "</td>");
                     out.println("</tr>");
                  }
               }
            }
        }if(seller != null){
            for (Receipt rec : receipt) {
              for(SOrder off : offer){
                    if (rec.getoId() == off.getoId() && seller.getsEmail().equals(off.getpSeller())){ 
                     out.println("<tr>");
                     out.println("<td>" + rec.getrId() + "</td>");
                     out.println("<td>" + rec.getrFeedback() + "</td>");
                     out.println("<td>" + rec.getrRating() + "</td>");
                     out.println("<td>" + sdf.format(rec.getrDateOfSale()) + "</td>");
                     out.println("<td>" + rec.getoId() + "</td>");
                     out.println("</tr>");
                  }
               }
            }
        }if(staff != null){
            for (Receipt rec : receipt) {
                     out.println("<tr>");
                     out.println("<td>" + rec.getrId() + "</td>");
                     out.println("<td>" + rec.getrFeedback() + "</td>");
                     out.println("<td>" + rec.getrRating() + "</td>");
                     out.println("<td>" + sdf.format(rec.getrDateOfSale()) + "</td>");
                     out.println("<td>" + rec.getoId() + "</td>");
                     out.println("</tr>");
                }
            }
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
