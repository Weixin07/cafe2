/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Property;

import Seller.Seller;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DeleteProperty", urlPatterns = {"/DeleteProperty"})
public class DeleteProperty extends HttpServlet {

    @EJB
    private PropertyFacade pf;
       
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession s = request.getSession();
        Seller seller = (Seller)s.getAttribute("seller");
        Property property = new Property();
        
        
        try (PrintWriter out = response.getWriter()) {
               
            try{
            int pId = Integer.parseInt(request.getParameter("pId"));
            
     
            property.setpId(pId);
            pf.remove(property);
          
           
                String c ="green";
                request.setAttribute("messageColor", c);
                request.setAttribute("messageLabelText", "Property Successfully Deleted!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", seller.getsName());
                request.getRequestDispatcher("deleteProperty.jsp").include(request, response);
            }catch(Exception e){
                
                request.setAttribute("messageLabelText","Invalid Property Details, Please Try Again!" + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", seller.getsName());
                request.getRequestDispatcher("deleteProperty.jsp").include(request, response);
                
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
