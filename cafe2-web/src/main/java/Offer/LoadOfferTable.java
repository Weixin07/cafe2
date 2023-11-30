/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Offer;

import Property.Property;
import Property.PropertyFacade;
import Seller.Seller;
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

@WebServlet(name = "LoadOfferTable", urlPatterns = {"/LoadOfferTable"})
public class LoadOfferTable extends HttpServlet {

    @EJB
    OfferFacade of;
    @EJB
    PropertyFacade pf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession s = request.getSession();
            Seller seller = (Seller)s.getAttribute("seller");
            
           
            List<Offer> offer = of.findAll();
            List<Property> property = pf.findAll();

            for (Offer off : offer) {
                for(Property prop : property){
                if(prop.getpId() == off.getpId() && seller.getsEmail().equals(prop.getpSeller()) && off.getoStatus()=='P'){
                out.println("<tr>");
                out.println("<td>" + off.getoId() + "</td>");
                out.println("<td>" + off.getpId() + "</td>");
                out.println("<td>" + off.getpAddress() + "</td>");
                out.println("<td>" + off.getbEmail() + "</td>");
                out.println("</tr>");
            }
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