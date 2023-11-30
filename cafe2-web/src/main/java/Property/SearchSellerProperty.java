/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Property;

import Seller.Seller;
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

/**
 *
 * @author pc
 */
@WebServlet(name = "SearchSellerProperty", urlPatterns = {"/SearchSellerProperty"})
public class SearchSellerProperty extends HttpServlet {
    
    @EJB
    PropertyFacade pf ;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        try (PrintWriter out = response.getWriter()) {
        String searchAddress = request.getParameter("searchAddress");
        HttpSession s = request.getSession();
        Seller seller = (Seller)s.getAttribute("seller");
        
        try{
            
            // Retrieve the filtered property data based on the search address from the database
            List<Property> propList = pf.findAll();
            List<Property> filteredProperties = new ArrayList<>();
            String pAddress = null;
            boolean pFound = false;
            
            for(Property property : propList){
                if (property.getpAddress().toLowerCase().contains(searchAddress.toLowerCase()) && property.getpSeller().equals(seller.getsEmail())) {
                    filteredProperties.add(property);
                    pFound = true;
                }
                pAddress = property.getpAddress();
            }
            
            if(pFound==false){
                request.setAttribute("messageLabelText","No Property Found, Please Try Again!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", seller.getsName());
                    request.setAttribute("hidePropertyTableLabel", true);
                    request.getRequestDispatcher("searchProperty.jsp").include(request, response);
            }else{
            
            // Set the filtered property data as an attribute in the request or session scope
            request.setAttribute("filteredProperties", filteredProperties);
            request.setAttribute("uNameLabelText", (seller.getsName()));
            request.setAttribute("hidePropertyTableLabel", true);}
            // Forward the request to the searchProperty.jsp page
            request.getRequestDispatcher("searchProperty.jsp").forward(request, response);
            }catch(Exception e){
                request.setAttribute("messageLabelText","Error fetching Property, Please Try Again!" + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", seller.getsName());
                request.setAttribute("hidePropertyTableLabel", true);
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
