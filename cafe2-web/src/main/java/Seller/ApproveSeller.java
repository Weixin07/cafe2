/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seller;

import Staff.*;
import Staff.Staff;
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
@WebServlet(name = "ApproveSeller", urlPatterns = {"/ApproveSeller"})
public class ApproveSeller extends HttpServlet {

        @EJB
        SellerFacade sf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        Staff staff = (Staff)s.getAttribute("staff");
        
        try{
  
        String sEmail = request.getParameter("sEmail");
        String sName = request.getParameter("sName");
        String sPhoneNo = request.getParameter("sPhoneNo");
        int sAge = Integer.parseInt(request.getParameter("sAge"));
        char sGender = request.getParameter("sGender").charAt(0);
        String sAddress = request.getParameter("sAddress");
        String sPass = request.getParameter("sPass");

        Seller seller = new Seller();
        
                if(sName.length()<3){
                    request.setAttribute("messageLabelText", "Make sure your Name is at least 3 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", staff.getStName());
                    request.getRequestDispatcher("approveSeller.jsp").include(request, response);
                    
                }else if(sEmail.length()<8){
                    request.setAttribute("messageLabelText", "Make sure your Email is at least 8 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", staff.getStName());
                    request.getRequestDispatcher("approveSeller.jsp").include(request, response);
                    
                }else if(sPass.length()<8 || sPass.length()>16){
                    request.setAttribute("messageLabelText", "Make sure your Password is at least 8 characters and max 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", staff.getStName());
                    request.getRequestDispatcher("approveSeller.jsp").include(request, response);
                
                }else if(sPhoneNo.length() != 9){
                    request.setAttribute("messageLabelText", "Make sure your Phone No. is exactly 9 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", staff.getStName());
                    request.getRequestDispatcher("approveSeller.jsp").include(request, response);
                
                }else if(sAge < 18 || sAge>100){
                    request.setAttribute("messageLabelText", "Sorry, We do not allow Minors to access!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", staff.getStName());
                    request.getRequestDispatcher("approveSeller.jsp").include(request, response);
                
                }else if(sAddress.length()<16 || sAddress.length()>64){
                    request.setAttribute("messageLabelText", "Make sure your Address is at least 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", staff.getStName());
                    request.getRequestDispatcher("approveSeller.jsp").include(request, response);
                }else if(sPass.length()< 8|| sPass.length() >16){
                    request.setAttribute("messageLabelText", "Make sure your password is between 8 and 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", staff.getStName());
                    request.getRequestDispatcher("approveSeller.jsp").include(request, response);
                }
                else{
                   
            
            // Save the property details in the database
                    seller.setsEmail(sEmail);
                    seller.setsPassword(sPass);
                    seller.setsName(sName);
                    seller.setsPhoneNo(Integer.parseInt(sPhoneNo));
                    seller.setsAddress(sAddress);
                    seller.setsAge(sAge);
                    seller.setsGender(sGender);
                    seller.setsApproval('A');
                    sf.edit(seller);
                    
                //request.setAttribute("property", property);
                String c ="green";
                request.setAttribute("messageColor", c);
                request.setAttribute("messageLabelText", "Seller Approved Successfully!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", staff.getStName());
                request.getRequestDispatcher("approveSeller.jsp").include(request, response);
          
                }
            }catch(Exception e){
                
                request.setAttribute("messageLabelText","Invalid Seller Details, Please Try Again!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", staff.getStName());
                request.getRequestDispatcher("approveSeller.jsp").include(request, response);
                
            }
        
        try (PrintWriter out = response.getWriter()) {
           
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