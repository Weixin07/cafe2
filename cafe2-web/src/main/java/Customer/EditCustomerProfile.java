package Customer;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditCustomerProfile", urlPatterns = {"/EditCustomerProfile"})
public class EditCustomerProfile extends HttpServlet {

    @EJB
    private CustomerFacade cf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession s = request.getSession(false);

        String cEmail = request.getParameter("cEditEmail");
        String cCurrentPassword = request.getParameter("cEditCurrentPassword");
        String cNewPassword = request.getParameter("cEditNewPassword");
        String cConfirmPassword = request.getParameter("cEditConfirmPassword");

        Customer customer = (Customer) s.getAttribute("customer");

        try (PrintWriter out = response.getWriter()) {
            try {

                if (cCurrentPassword.length() < 1 || cNewPassword.length() < 1 || cConfirmPassword.length() < 1) {
                    request.setAttribute("messageLabelText", "Please type in the password(s).");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", cEmail);
                    request.getRequestDispatcher("EditCustomerProfile.jsp").include(request, response);
                } else if (!customer.getcPassword().equals(cCurrentPassword)) {
                    request.setAttribute("messageLabelText", "Incorrect password. Please try again." + cCurrentPassword + customer.getcPassword());
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", cEmail);
                    request.getRequestDispatcher("EditCustomerProfile.jsp").include(request, response);
                } else if (!cNewPassword.equals(cConfirmPassword)) {
                    request.setAttribute("messageLabelText", "The conirmed password do not match the new password. Please try again.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", cEmail);
                    request.getRequestDispatcher("EditCustomerProfile.jsp").include(request, response);

                } else {
                    customer.setcEmail(cEmail);
                    customer.setcPassword(cNewPassword);
                    cf.edit(customer);

                    String c = "green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Successfully edited.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", cEmail);
                    request.getRequestDispatcher("EditCustomerProfile.jsp").include(request, response);
                }
            } catch (ServletException | IOException e) {
                request.setAttribute("messageLabelText", "Error. Please try again." + cEmail + cCurrentPassword + customer.getcEmail() + customer.getcPassword()+ e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", cEmail);
                request.getRequestDispatcher("EditCustomerProfile.jsp").include(request, response);
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
