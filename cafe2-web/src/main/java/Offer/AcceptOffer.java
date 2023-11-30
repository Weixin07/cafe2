/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Offer;

import Property.Property;
import Property.PropertyFacade;
import Receipt.Receipt;
import Receipt.ReceiptFacade;
import Seller.Seller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "AcceptOffer", urlPatterns = {"/AcceptOffer"})
public class AcceptOffer extends HttpServlet {

@EJB
OfferFacade of;
@EJB
ReceiptFacade rf;
@EJB 
PropertyFacade pf;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        Seller seller = (Seller)s.getAttribute("seller");
        Offer offer = new Offer();
        Property property = new Property();
        
        try{
       
        
        int oId = Integer.parseInt(request.getParameter("oId"));
        int pId = Integer.parseInt(request.getParameter("pId"));
        String pAddress = request.getParameter("pAddress");
        String bEmail = request.getParameter("bEmail");
        
        offer.setoId(oId);
        offer.setpId(pId);
        offer.setbEmail(bEmail);
        offer.setpAddress(pAddress);
        offer.setpSeller(seller.getsEmail());
        offer.setoStatus('A');
        of.edit(offer);
        
        of.deleteOffersByPropertyId(pId);
        
        int rId = rf.count() + 1;
        String rFeedback = "";
        int rRating = 0 ;
        Date rDateOfSale = new Date();
      
        rf.create(new Receipt(rId, rFeedback, rRating, rDateOfSale, oId));
        
        property.setpId(pId);
        pf.remove(property);
        
        try (PrintWriter out = response.getWriter()) {
            String c ="green";
            request.setAttribute("messageColor", c);
            request.setAttribute("messageLabelText","Offer Successfully Accepted, Your Property has now been Sold on UNREAL ESTATE, Thank you for using our services, Please come again!");
            request.setAttribute("hideMessageLabel", false);
            request.setAttribute("uNameLabelText", seller.getsEmail());
            request.getRequestDispatcher("acceptOffer.jsp").include(request, response);
            }
        }catch(Exception e){
            request.setAttribute("messageLabelText","Offer Successfully Accepted, Your Property has now been Sold on UNREAL ESTATE, Thank you for using our services, Please come again!");
            request.setAttribute("hideMessageLabel", false);
            request.setAttribute("uNameLabelText", seller.getsEmail());
            request.getRequestDispatcher("acceptOffer.jsp").include(request, response);
            
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
