package SStaff;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditSStaffProfile", urlPatterns = {"/EditSStaffProfile"})
public class EditSStaffProfile extends HttpServlet {

    @EJB
    private SStaffFacade ssf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession s = request.getSession(false);

        String ssUsername = request.getParameter("ssEditUsername");
        String ssCurrentPassword = request.getParameter("ssEditCurrentPassword");
        String ssNewPassword = request.getParameter("ssEditNewPassword");
        String ssConfirmPassword = request.getParameter("ssEditConfirmPassword");

        SStaff sstaff = (SStaff) s.getAttribute("sstaff");

        try (PrintWriter out = response.getWriter()) {
            try {

                if (ssCurrentPassword.length() < 0 || ssNewPassword.length() < 0 || ssConfirmPassword.length() < 0) {
                    request.setAttribute("messageLabelText", "The new password should be more than 1 character.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", ssUsername);
                    request.getRequestDispatcher("EditSStaffProfile.jsp").include(request, response);
                } else if (!sstaff.getSsPassword().equals(ssCurrentPassword)) {
                    request.setAttribute("messageLabelText", "Incorrect password. Please try again." + ssCurrentPassword + sstaff.getSsPassword());
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", ssUsername);
                    request.getRequestDispatcher("EditSStaffProfile.jsp").include(request, response);
                } else if (!ssNewPassword.equals(ssConfirmPassword)) {
                    request.setAttribute("messageLabelText", "The conirmed password to not match the new password. Please try again.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", ssUsername);
                    request.getRequestDispatcher("EditSStaffProfile.jsp").include(request, response);

                } else {

                    sstaff.setSsUsername(ssUsername);
                    sstaff.setSsPassword(ssNewPassword);
                    ssf.edit(sstaff);

                    String c = "green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Successfully edited.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", ssUsername);
                    request.getRequestDispatcher("EditSStaffProfile.jsp").include(request, response);
                }
            } catch (ServletException | IOException e) {
                request.setAttribute("messageLabelText", "Error. Please try again." + ssUsername + ssCurrentPassword + sstaff.getSsUsername() + sstaff.getSsPassword() + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", ssUsername);
                request.getRequestDispatcher("EditSStaffProfile.jsp").include(request, response);
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
