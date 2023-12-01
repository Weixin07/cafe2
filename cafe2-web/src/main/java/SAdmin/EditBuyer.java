package SAdmin;

import sAdmin.SAdmin;
import sAdmin.SAdminFacade;
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

@WebServlet(name = "EditBuyer", urlPatterns = {"/EditBuyer"})
public class EditBuyer extends HttpServlet {

    @EJB
    SAdminFacade bf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        MStaff staff = (MStaff)s.getAttribute("staff");
        SAdmin buy = new SAdmin();
        
        try{
            String bEmail = request.getParameter("bEmail");
            String bPass = request.getParameter("bPass");

            if(bEmail.length()<8){
                request.setAttribute("messageLabelText", "Make sure your Email is at least 8 characters");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", staff.getStName());
                request.getRequestDispatcher("editBuyer.jsp").include(request, response);

            }else if(bPass.length()<8 || bPass.length()>16){
                request.setAttribute("messageLabelText", "Make sure your Password is at least 8 characters and max 16 characters");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", staff.getStName());
                request.getRequestDispatcher("editBuyer.jsp").include(request, response);   
            }else{

                buy.setbUsername(bEmail);
                buy.setbPassword(bPass);
                bf.edit(buy);
                // Save the property details in the database


            //request.setAttribute("property", property);
            String c ="green";
            request.setAttribute("messageColor", c);
            request.setAttribute("messageLabelText", "Buyer Info Successfully Edited!");
            request.setAttribute("hideMessageLabel", false);
            request.setAttribute("uNameLabelText", staff.getStName());
            request.getRequestDispatcher("editBuyer.jsp").include(request, response);
            }
        }   catch(Exception e){

            request.setAttribute("messageLabelText","Invalid Buyer Details, Please Try Again!" + e);
            request.setAttribute("hideMessageLabel", false);
            request.setAttribute("uNameLabelText", staff.getStName());
            request.getRequestDispatcher("editBuyer.jsp").include(request, response);

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
