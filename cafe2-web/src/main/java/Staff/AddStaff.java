/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Staff;

import MStaff.MStaffFacade;
import MStaff.MStaff;
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
@WebServlet(name = "AddStaff", urlPatterns = {"/AddStaff"})
public class AddStaff extends HttpServlet {

    @EJB
    MStaffFacade stf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try{
  
        String stName = request.getParameter("stName");
        String stEmail = request.getParameter("stEmail");
        String stPass = request.getParameter("stPass");
        int stAge = Integer.parseInt(request.getParameter("stAge"));
        String stPhoneNo = request.getParameter("stPhoneNo");
        String stAddress = request.getParameter("stAddress");
     
        
                if(stName.length()<3){
                    request.setAttribute("messageLabelText", "Make sure your Name is at least 3 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", "Master");
                    request.getRequestDispatcher("addStaff.jsp").include(request, response);
                    
                }else if(stEmail.length()<8){
                    request.setAttribute("messageLabelText", "Make sure your Email is at least 8 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", "Master");
                    request.getRequestDispatcher("addStaff.jsp").include(request, response);
                    
                }else if(stPass.length()<8 || stPass.length()>16){
                    request.setAttribute("messageLabelText", "Make sure your Password is at least 8 characters and max 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", "Master");
                    request.getRequestDispatcher("addStaff.jsp").include(request, response);
                
                }else if(stPhoneNo.length() != 9){
                    request.setAttribute("messageLabelText", "Make sure your Phone No. is exactly 9 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", "Master");
                    request.getRequestDispatcher("addStaff.jsp").include(request, response);
                
                }else if(stAge < 18 || stAge>100){
                    request.setAttribute("messageLabelText", "Sorry, We do not hire Minors!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", "Master");
                    request.getRequestDispatcher("addStaff.jsp").include(request, response);
                
                }else if(stAddress.length()<16 || stAddress.length()>64){
                    request.setAttribute("messageLabelText", "Make sure your Address is at least 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", "Master");
                    request.getRequestDispatcher("addStaff.jsp").include(request, response);
                }else{
                   
            
            // Save the property details in the database
                stf.create(new MStaff(stEmail,stName,stPass,stAge,stAddress,Integer.parseInt(stPhoneNo)));
                
                //request.setAttribute("property", property);
                String c ="green";
                request.setAttribute("messageColor", c);
                request.setAttribute("messageLabelText", "Staff Member Successfully Created!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", "Master");
                request.getRequestDispatcher("addStaff.jsp").include(request, response);
          
                }
            }catch(Exception e){
                
                request.setAttribute("messageLabelText","Invalid Staff Member Details, Please Try Again!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", "Master");
                request.getRequestDispatcher("addStaff.jsp").include(request, response);
                
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
