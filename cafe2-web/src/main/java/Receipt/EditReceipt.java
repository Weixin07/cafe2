/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Receipt;

import Buyer.Buyer;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditReceipt", urlPatterns = {"/EditReceipt"})
public class EditReceipt extends HttpServlet {
    
    @EJB 
    ReceiptFacade rf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        Buyer buyer = (Buyer)s.getAttribute("user");

        try{
        int rId = Integer.parseInt(request.getParameter("rId"));
        String rFeedback = request.getParameter("rFeedback");
        int rRating = Integer.parseInt(request.getParameter("rRating"));
        String rDateOfSale = request.getParameter("rDateOfSale");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        Date rDOS = sdf.parse(rDateOfSale);
        
        int oId = Integer.parseInt(request.getParameter("oId"));

        
                if(rFeedback.length()< 8){
                    request.setAttribute("messageLabelText", "Invalid Feedback, Please Write a few words");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", buyer.getbUsername());
                    request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
                  
                
                }else if(rRating < 1 || rRating > 5){
                    request.setAttribute("messageLabelText", "Make sure rating is between 1 & 5");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", buyer.getbUsername());
                    request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
                
                }else{
                    Receipt rec = new Receipt();
                    rec.setId(rId);
                    rec.setrFeedback(rFeedback);
                    rec.setrRating(rRating);
                    rec.setrDateOfSale(rDOS);
                    rec.setoId(oId);
                    // Save the property details in the database
                    rf.edit(rec);
                    
                //request.setAttribute("property", property);
                String c ="green";
                request.setAttribute("messageColor", c);
                request.setAttribute("messageLabelText", "Thank you for the Feedback!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", buyer.getbUsername());
                request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
                }
            }catch(Exception e){
                
                request.setAttribute("messageLabelText","Invalid Feedback Details, Please Try Again!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", buyer.getbUsername());
                request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
                
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
