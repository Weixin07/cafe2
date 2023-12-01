package MStaff;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterMStaff", urlPatterns = {"/RegisterMStaff"})
public class RegisterMStaff extends HttpServlet {

    @EJB
    private MStaffFacade msf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String msUsername = request.getParameter("msUsername");
        String msPassword = request.getParameter("msPassword");

        try (PrintWriter out = response.getWriter()) {
            try {
                if (msPassword.length() < 1) {

                    request.setAttribute("messageLabelText", "Password should be more than 1 character. Please try again.");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("register.jsp").include(request, response);

                } else {
                    msf.create(new MStaff(msUsername, msPassword));

                    String c = "green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Registration completed.");
                    request.setAttribute("hideMessageLabel", false);
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }
            } catch (ServletException | IOException e) {
                request.setAttribute("messageLabelText", "Error. Please try again.");
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
