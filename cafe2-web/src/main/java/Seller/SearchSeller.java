/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seller;

import SStaff.SStaffFacade;
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

@WebServlet(name = "SearchSeller", urlPatterns = {"/SearchSeller"})
public class SearchSeller extends HttpServlet {
    
    @EJB
    SStaffFacade sf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
              
        
        try (PrintWriter out = response.getWriter()) {
     
        HttpSession s = request.getSession();
        MStaff staff = (MStaff)s.getAttribute("staff");
        String searchSeller = request.getParameter("searchSeller");
        
        try{
            
       
            List<SStaff> sellerList = sf.findAll();
            List<SStaff> filteredSeller = new ArrayList<>();
            boolean pFound = false;
            
            for(SStaff sell : sellerList){
                if (sell.getsEmail().toLowerCase().contains(searchSeller.toLowerCase())) {
                    filteredSeller.add(sell);
                    pFound = true;
                }
            }
            
            if(pFound==false){
                request.setAttribute("messageLabelText","No Seller Found, Please Try Again!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", staff.getStName());
                    request.getRequestDispatcher("searchSeller.jsp").include(request, response);
            }else{
        
            request.setAttribute("filteredSeller", filteredSeller);
            request.setAttribute("uNameLabelText", (staff.getStName()));
            request.setAttribute("hideSellerTableLabel", true);}
          
            request.getRequestDispatcher("searchSeller.jsp").forward(request, response);
            }catch(Exception e){
                request.setAttribute("messageLabelText","Error fetching Seller, Please Try Again!" + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", staff.getStName());
                request.setAttribute("hideSellerTableLabel", true);
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
