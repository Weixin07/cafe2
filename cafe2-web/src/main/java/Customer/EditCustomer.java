package Customer;

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

@WebServlet(name = "EditCustomer", urlPatterns = {"/EditCustomer"})
public class EditCustomer extends HttpServlet {

    @EJB
    CustomerFacade cf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession s = request.getSession();
            MStaff mstaff = (MStaff) s.getAttribute("mstaff");
            Customer customer = new Customer();

            try {
                String cEmail = request.getParameter("cEmail");
                String cPassword = request.getParameter("cPassword");
                String cName = request.getParameter("cName");
                int cAge = Integer.parseInt(request.getParameter("cAge"));
                String cGender = request.getParameter("cGender");

                if (cEmail.length() < 1) {
                    request.setAttribute("messageLabelText", "Please input the email.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                    request.getRequestDispatcher("editSStaff.jsp").include(request, response);
                } else if (cPassword.length() < 1) {
                    request.setAttribute("messageLabelText", "Please input the password.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                    request.getRequestDispatcher("editSStaff.jsp").include(request, response);
                } else if (cName.length() < 1) {
                    request.setAttribute("messageLabelText", "Please input the name.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                    request.getRequestDispatcher("editSStaff.jsp").include(request, response);
                } else if (cAge < 1) {
                    request.setAttribute("messageLabelText", "Please input the age.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                    request.getRequestDispatcher("editSStaff.jsp").include(request, response);
                } else if (cGender.length() < 1) {
                    request.setAttribute("messageLabelText", "Please input the gender.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                    request.getRequestDispatcher("editSStaff.jsp").include(request, response);
                } else {
                    // Save details in the database
                    customer.setcEmail(cEmail);
                    customer.setcPassword(cPassword);
                    customer.setcName(cName);
                    customer.setcAge(cAge);
                    customer.setcGender(cGender);
                    customer.setMsUsername(mstaff.getMsUsername());
                    cf.edit(customer);

                    String c = "green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Customer's detailed successfully edited.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                    request.getRequestDispatcher("EditCustomer.jsp").include(request, response);
                }
            } catch (ServletException | IOException e) {

                request.setAttribute("messageLabelText", "Error. Please try again." + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                request.getRequestDispatcher("EditCustomer.jsp").include(request, response);

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
