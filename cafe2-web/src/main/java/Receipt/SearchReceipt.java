/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Receipt;

import Receipt.*;
import SStaff.SStaff;
import MStaff.MStaff;
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


@WebServlet(name = "SearchReceipt", urlPatterns = {"/SearchReceipt"})
public class SearchReceipt extends HttpServlet {
 
    @EJB 
    ReceiptFacade stf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        HttpSession s = request.getSession();
        MStaff staff = (MStaff)s.getAttribute("staff");
        
        
        try (PrintWriter out = response.getWriter()) {
        try{
            String searchReceipt = request.getParameter("searchReceipt");
            // Retrieve the filtered property data based on the search address from the database
            List<Receipt> receiptList = stf.findAll();
            List<Receipt> filteredReceipt = new ArrayList<>();
            boolean pFound = false;
            
            
            for(Receipt sTf : receiptList){
                if (sTf.getrId().toString().equals(searchReceipt)) {
                    filteredReceipt.add(sTf);
                    pFound = true;
                    }
                }
            
            if(pFound==false){
                request.setAttribute("messageLabelText","No Receipt Found, Please Try Again!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", staff.getStName());
                    request.getRequestDispatcher("feedbackAnalysis.jsp").include(request, response);
            }else{
            
            // Set the filtered property data as an attribute in the request or session scope
            request.setAttribute("filteredReceipt", filteredReceipt);
            request.setAttribute("uNameLabelText", staff.getStName());
            request.setAttribute("hideReceiptTableLabel", true);}
            // Forward the request to the searchProperty.jsp page
            request.getRequestDispatcher("feedbackAnalysis.jsp").forward(request, response);
            }catch(Exception e){
                request.setAttribute("messageLabelText","Error fetching Receipt, Please Try Again!" + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", staff.getStName());
                request.setAttribute("hideReceiptTableLabel", true);
        
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
