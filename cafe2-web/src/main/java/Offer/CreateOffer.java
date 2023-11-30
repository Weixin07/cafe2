/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Offer;

import Buyer.Buyer;
import Property.Property;
import Property.PropertyFacade;
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


@WebServlet(name = "CreateOffer", urlPatterns = {"/CreateOffer"})
public class CreateOffer extends HttpServlet {

@EJB
OfferFacade of;
@EJB 
PropertyFacade pf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        Buyer buyer = (Buyer)s.getAttribute("user");
        
        try (PrintWriter out = response.getWriter()) {
        int oId = of.count() + 1;
        int pId = Integer.parseInt(request.getParameter("pId"));
        String pAddress = request.getParameter("pAddress");
        String pSeller = request.getParameter("pSeller");
        boolean pFound = false;
        
        List<Offer> offer = of.findAll();
        int lastOfferId = of.getLastOfferId();
        
        for(Offer off : offer){
            if(off.getoStatus()!='A' &&  off.getbEmail().toLowerCase().equals(buyer.getbUsername().toLowerCase()) && off.getpId() == pId){
                pFound = true;
                break;
            }
        }
        
        if(pFound){
        request.setAttribute("messageLabelText","Bid Not Placed, You've already bid for this Property.");
        request.setAttribute("hideMessageLabel", false);
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
        request.setAttribute("uNameLabelText", buyer.getbUsername());
       
        List<Property> properties = pf.findAll();
        request.setAttribute("properties", properties);
        request.getRequestDispatcher("propertyListings.jsp").include(request, response);
            
        } else{
        of.create(new Offer(lastOfferId+1, pId, pAddress, pSeller, buyer.getbUsername(), 'P'));
        
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
        request.setAttribute("uNameLabelText", buyer.getbUsername());
       
        List<Property> properties = pf.findAll();
        request.setAttribute("properties", properties);
        request.getRequestDispatcher("propertyListings.jsp").include(request, response);

        out.println("<script>");
        out.println("alert('Bid Placed Successfully!');");
        out.println("</script>");
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
