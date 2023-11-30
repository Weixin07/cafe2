/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonControllers;

import java.io.IOException;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Buyer.Buyer;
import Buyer.BuyerFacade;
import Seller.Seller;
import Seller.SellerFacade;
import Staff.Staff;
import Staff.StaffFacade;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
    @EJB
    BuyerFacade bf;
    @EJB
    SellerFacade sf;
    @EJB
    StaffFacade stf;
    
    Seller sFound;
    Buyer bFound;
    Staff stFound;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
       HttpSession s = request.getSession();
        
        String eMail = request.getParameter("eMail");
        String pass = request.getParameter("pass");
         
        bFound = bf.find(eMail);
        sFound = sf.find(eMail);
        stFound = stf.find(eMail);
        s.setAttribute("user", bFound);
        s.setAttribute("seller", sFound);
        s.setAttribute("staff", stFound);
        s.setAttribute("admin", "admin");

        
            switch (getType(eMail, pass)) {
                case "Seller":
                    request.setAttribute("hideSMRELabel", false);
                    request.setAttribute("hideSAOLabel", false);
                    request.setAttribute("hideSEPHLabel", false);
                    request.setAttribute("hideEPLabel", false);
                    request.setAttribute("hideBVPHLabel", true);
                    request.setAttribute("hideSMBLabel", true);
                    request.setAttribute("hideSMSLabel", true);
                    request.setAttribute("hideSFBALabel", true);
                    request.setAttribute("hideAMSLabel", true);
                    request.setAttribute("uNameLabelText", sFound.getsName());
                    request.setAttribute("hideLoginLabel", true);
                    request.setAttribute("hideNameLabel", false);
                    request.setAttribute("hidePropertyListingsLabel", false);
                    request.getRequestDispatcher("home.jsp").include(request, response);
                    break;
                case "Buyer":
                    request.setAttribute("hideSMRELabel", true);
                    request.setAttribute("hideSAOLabel", true);
                    request.setAttribute("hideSEPHLabel", true);
                    request.setAttribute("hideEPLabel", false);
                    request.setAttribute("hideBVPHLabel", false);
                    request.setAttribute("hideSMBLabel", true);
                    request.setAttribute("hideSMSLabel", true);
                    request.setAttribute("hideSFBALabel", true);
                    request.setAttribute("hideAMSLabel", true);
                    request.setAttribute("uNameLabelText", bFound.getbUsername());
                    request.setAttribute("hideLoginLabel", true);
                    request.setAttribute("hideNameLabel", false);
                    request.setAttribute("hidePropertyListingsLabel", false);
                    request.getRequestDispatcher("home.jsp").include(request, response);
                    break;
                case "Staff":
                    request.setAttribute("hideSMRELabel", true);
                    request.setAttribute("hideSAOLabel", true);
                    request.setAttribute("hideSEPHLabel", true);
                    request.setAttribute("hideEPLabel", false);
                    request.setAttribute("hideBVPHLabel", true);
                    request.setAttribute("hideSMBLabel", false);
                    request.setAttribute("hideSMSLabel", false);
                    request.setAttribute("hideSFBALabel", false);
                    request.setAttribute("hideAMSLabel", true);
                    request.setAttribute("uNameLabelText", stFound.getStName());
                    request.setAttribute("hideLoginLabel", true);
                    request.setAttribute("hideNameLabel", false);
                    request.setAttribute("hidePropertyListingsLabel", false);
                    request.getRequestDispatcher("home.jsp").include(request, response);
                    break;
                case "Admin":
                    request.setAttribute("hideSMRELabel", true);
                    request.setAttribute("hideSAOLabel", true);
                    request.setAttribute("hideSEPHLabel", true);
                    request.setAttribute("hideEPLabel", true);
                    request.setAttribute("hideBVPHLabel", true);
                    request.setAttribute("hideSMBLabel", true);
                    request.setAttribute("hideSMSLabel", true);
                    request.setAttribute("hideSFBALabel", true);
                    request.setAttribute("hideAMSLabel", false);
                    request.setAttribute("uNameLabelText", "Welcome, Master");
                    request.setAttribute("hideLoginLabel", true);
                    request.setAttribute("hideNameLabel", false);
                    request.setAttribute("hidePropertyListingsLabel", false);
                    request.getRequestDispatcher("home.jsp").include(request, response);
                    break;
                default:
                    request.setAttribute("messageLabelText","Invalid Credentials, Please Try Again!");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("login.jsp").include(request, response);
                    break;
                }
            
        }
    
    private String getType(String email, String password) {
    if (sFound != null && sFound.getsEmail().equals(email) && sFound.getsPassword().equals(password) && sFound.getsApproval() == 'A') {
        return "Seller";
    }else if (bFound != null && bFound.getbUsername().equals(email) && bFound.getbPassword().equals(password)) {
        return "Buyer";
    }else if (stFound != null && stFound.getStEmail().equals(email) && stFound.getStPassword().equals(password)) {
        return "Staff";
    } else if(email.toLowerCase().equals("admin") && password.equals("nimda")) {
        return "Admin";
    }else{
        return "Invalid";
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
