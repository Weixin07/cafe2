package Seller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "SellerRegister", urlPatterns = {"/SellerRegister"})
public class SellerRegister extends HttpServlet {

    @EJB
    private SellerFacade sf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String sEmail = request.getParameter("sEmail");
        String sName = request.getParameter("sName");
        String sPhoneNo = request.getParameter("sPhoneNo");
        int sAge = Integer.parseInt(request.getParameter("sAge"));
        char sGender = request.getParameter("sGender").charAt(0);
        String sAddress = request.getParameter("sAddress");
        String sPass = request.getParameter("sPass");
        
        
        try (PrintWriter out = response.getWriter()) {
          
            try{
                if(sName.length()<3){
                    request.setAttribute("messageLabelText", "Make sure your Name is at least 3 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                    
                }else if(sEmail.length()<8){
                    request.setAttribute("messageLabelText", "Make sure your Email is at least 8 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                    
                }else if(sPass.length()<8 || sPass.length()>16){
                    request.setAttribute("messageLabelText", "Make sure your Password is at least 8 characters and max 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }else if(sPhoneNo.length() != 9){
                    request.setAttribute("messageLabelText", "Make sure you key in exactly 9 characters which are ahead of the country code (+60)");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }else if(sAddress.length()<16 || sAddress.length()>64){
                    request.setAttribute("messageLabelText", "Make sure your Address is at least 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }else if(sAge < 18 || sAge >100){
                    request.setAttribute("messageLabelText", "UNREAL ESTATE only allows individuals above the age of 18");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }else{
                
     
                sf.create(new Seller(sEmail, sName, sPass, Integer.parseInt(sPhoneNo), sAge, sGender, sAddress,'P'));
                
                String c ="green";
                request.setAttribute("messageColor", c);
                request.setAttribute("messageLabelText", "Successful Registration! Account Pending Approval.");
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
