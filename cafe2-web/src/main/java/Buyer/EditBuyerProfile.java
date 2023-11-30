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
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author pc
 */


@WebServlet(name = "EditBuyerProfile", urlPatterns = {"/EditBuyerProfile"})
public class EditBuyerProfile extends HttpServlet {
    
    @EJB
    private BuyerFacade bf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession s = request.getSession(false);
        
        String bEmail = request.getParameter("bEditEmail");
        String bCurrentPass = request.getParameter("bEditCurrentPass");
        String bNewPass = request.getParameter("bEditNewPass");
        String bConfNewPass = request.getParameter("bEditConfNewPass");
        Buyer buyer = (Buyer)s.getAttribute("user");
        
      
        try (PrintWriter out = response.getWriter()) {
           try{
               
                if(bCurrentPass.length()<8 || bCurrentPass.length()>16 || bNewPass.length()<8 || bNewPass.length()>16 || bConfNewPass.length()<8 || bConfNewPass.length()>16  ){
      
                    request.setAttribute("messageLabelText", "Make sure your new Password is between 8 and 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", bEmail);
                    request.getRequestDispatcher("editBuyerProfile.jsp").include(request, response);
                    
                }else if(!buyer.getbPassword().equals(bCurrentPass)){
                    request.setAttribute("messageLabelText", "Invalid Current Password entered, Please Try Again!" + bCurrentPass + buyer.getbPassword());
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", bEmail);
                    request.getRequestDispatcher("editBuyerProfile.jsp").include(request, response);
                }else if(!bNewPass.equals(bConfNewPass)){
                    request.setAttribute("messageLabelText", "Password Confirmation Fields are not Matching!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", bEmail);
                    request.getRequestDispatcher("editBuyerProfile.jsp").include(request, response);
                    
                }
                else{
                   
                    buyer.setbUsername(bEmail);
                    buyer.setbPassword(bNewPass);
                    bf.edit(buyer);
       
                    String c ="green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Successfully Edited!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", bEmail);
                    request.getRequestDispatcher("editBuyerProfile.jsp").include(request, response);
                }
            }catch(Exception e){
                request.setAttribute("messageLabelText","Invalid Details, Please Try Again my fren!" + bEmail + bCurrentPass + buyer.getbUsername() + buyer.getbPassword() + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", bEmail);
                request.getRequestDispatcher("editBuyerProfile.jsp").include(request, response);
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
