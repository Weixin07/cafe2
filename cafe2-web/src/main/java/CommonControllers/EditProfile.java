package CommonControllers;

import Buyer.Buyer;
import Seller.Seller;
import Staff.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditProfile", urlPatterns = {"/EditProfile"})
public class EditProfile extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession s = request.getSession();
            Buyer buyer = (Buyer)s.getAttribute("user");
            Seller seller = (Seller)s.getAttribute("seller");
            Staff staff = (Staff)s.getAttribute("staff");
            
            if(seller!=null){
                request.setAttribute("uNameLabelText", seller.getsEmail());
                request.setAttribute("nameLabelText", seller.getsName());
                request.setAttribute("phNoLabelText", seller.getsPhoneNo());
                request.setAttribute("addressLabelText", seller.getsAddress());
                request.setAttribute("ageLabelText", seller.getsAge());
                request.setAttribute("genderLabelText", seller.getsGender());
                request.getRequestDispatcher("editSellerProfile.jsp").include(request, response);
            }
            else if(buyer!=null){
            request.setAttribute("uNameLabelText", buyer.getbUsername());
            request.getRequestDispatcher("editBuyerProfile.jsp").include(request, response);
            }
            else if(staff!=null){
            request.setAttribute("uNameLabelText", staff.getStEmail());
            request.getRequestDispatcher("editStaffProfile.jsp").include(request, response);
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
