/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buyer;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "BuyerRegister", urlPatterns = {"/BuyerRegister"})
public class BuyerRegister extends HttpServlet {

    @EJB
    private BuyerFacade bf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String bEmail = request.getParameter("bEmail");
        String bPass = request.getParameter("bPass");
        
        
        try (PrintWriter out = response.getWriter()) {
            try{
                if(bPass.length()<8 || bPass.length()>16){
      
                    request.setAttribute("messageLabelText", "Make sure Password is between 8 and 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                    
                }
                else{
                bf.create(new Buyer(bEmail,bPass));
                
                String c ="green";
                request.setAttribute("messageColor", c);
                request.setAttribute("messageLabelText", "Successful Registration! You may now Sign-in.");
                request.setAttribute("hideMessageLabel", false);
                request.getRequestDispatcher("login.jsp").include(request, response);
                }
            }catch(Exception e){
                request.setAttribute("messageLabelText","Invalid Registration Details, Please Try Again!");
                request.setAttribute("hideMessageLabel", false);
                request.getRequestDispatcher("register.jsp").include(request, response);
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
