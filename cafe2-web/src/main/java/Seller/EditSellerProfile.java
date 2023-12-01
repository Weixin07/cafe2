/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seller;

import SStaff.SStaffFacade;
import SStaff.SStaff;
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
@WebServlet(name = "EditSellerProfile", urlPatterns = {"/EditSellerProfile"})
public class EditSellerProfile extends HttpServlet {

    @EJB
    private SStaffFacade sf;
        
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        HttpSession s = request.getSession(false);
        
        String sEmail = request.getParameter("sEditEmail");
        String sName = request.getParameter("sEditName");
        String sPhoneNo = request.getParameter("sEditPhone");
        int sAge = Integer.parseInt(request.getParameter("sEditAge"));
        char sGender = request.getParameter("sEditGender").charAt(0);
        String sAddress = request.getParameter("sEditAddress");
        String sCurrentPass = request.getParameter("sEditCurrentPass");
        String sNewPass = request.getParameter("sEditNewPass");
        String sConfNewPass = request.getParameter("sEditConfNewPass");
        SStaff seller = (SStaff)s.getAttribute("seller");
   

        try (PrintWriter out = response.getWriter()) {
             try{
               
                if(sName.length()<3){
                    request.setAttribute("messageLabelText", "Make sure your Name is at least 3 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sEmail);
                    request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
                    
                }else if(sPhoneNo.length() != 9){
                    request.setAttribute("messageLabelText", "Make sure your Phone No. is exactly 9 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sEmail);
                    request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
                }else if(sAddress.length()<16 || sAddress.length()>64){
                    request.setAttribute("messageLabelText", "Make sure your Address is at least 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sEmail);
                    request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
                }else if(sCurrentPass.length()<8 || sCurrentPass.length()>16 || sNewPass.length()<8 || sNewPass.length()>16 || sConfNewPass.length()<8 || sConfNewPass.length()>16  ){
                    request.setAttribute("messageLabelText", "Make sure your new Password is between 8 and 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sEmail);
                    request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
                    
                }else if(!seller.getsPassword().equals(sCurrentPass)){
                    request.setAttribute("messageLabelText", "Invalid Current Password entered, Please Try Again!" + sCurrentPass + seller.getsPassword());
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sEmail);
                    request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
                }else if(!sNewPass.equals(sConfNewPass)){
                    request.setAttribute("messageLabelText", "Password Confirmation Fields are not Matching!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sEmail);
                    request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
                }else if(sAge < 18 || sAge >100){
                    request.setAttribute("messageLabelText", "UNREAL ESTATE only allows individuals above the age of 18");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
                } 
                else{
                    
                    seller.setsEmail(sEmail);
                    seller.setsPassword(sNewPass);
                    seller.setsName(sName);
                    seller.setsPhoneNo(Integer.parseInt(sPhoneNo));
                    seller.setsAddress(sAddress);
                    seller.setsAge(sAge);
                    seller.setsGender(sGender);
                    seller.setsApproval('A');
                    sf.edit(seller);
       
                    String c ="green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Successfully Edited!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sEmail);
                    request.setAttribute("nameLabelText", seller.getsName());
                    request.setAttribute("phNoLabelText", seller.getsPhoneNo());
                    request.setAttribute("addressLabelText", seller.getsAddress());
                    request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
                }
            }catch(Exception e){
                request.setAttribute("messageLabelText","Invalid Details, Please Try Again!" + sEmail + sCurrentPass + seller.getsEmail() + seller.getsPassword() + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", sEmail);
                request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
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
