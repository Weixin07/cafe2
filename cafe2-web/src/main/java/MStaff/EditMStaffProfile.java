package MStaff;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditMStaffProfile", urlPatterns = {"/EditMStaffProfile"})
public class EditMStaffProfile extends HttpServlet {

    @EJB
    private MStaffFacade msf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession s = request.getSession(false);

        String msUsername = request.getParameter("msEditUsername");
        String msCurrentPassword = request.getParameter("msEditCurrentPassword");
        String msNewPassword = request.getParameter("msEditNewPassword");
        String msConfirmPassword = request.getParameter("msEditConfirmPassword");

        MStaff mstaff = (MStaff) s.getAttribute("mstaff");

        try (PrintWriter out = response.getWriter()) {
            try {

                if (msCurrentPassword.length() < 0 || msNewPassword.length() < 0 || msConfirmPassword.length() < 0) {
                    request.setAttribute("messageLabelText", "The new password should be more than 1 character.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", msUsername);
                    request.getRequestDispatcher("EditMStaffProfile.jsp").include(request, response);
                } else if (!mstaff.getMsPassword().equals(msCurrentPassword)) {
                    request.setAttribute("messageLabelText", "Incorrect password. Please try again." + msCurrentPassword + mstaff.getMsPassword());
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", msUsername);
                    request.getRequestDispatcher("EditMStaffProfile.jsp").include(request, response);
                } else if (!msNewPassword.equals(msConfirmPassword)) {
                    request.setAttribute("messageLabelText", "The conirmed password to not match the new password. Please try again.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", msUsername);
                    request.getRequestDispatcher("EditMStaffProfile.jsp").include(request, response);

                } else {

                    mstaff.setMsUsername(msUsername);
                    mstaff.setMsPassword(msNewPassword);
                    msf.edit(mstaff);

                    String c = "green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Successfully edited.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", msUsername);
                    request.getRequestDispatcher("EditMStaffProfile.jsp").include(request, response);
                }
            } catch (ServletException | IOException e) {
                request.setAttribute("messageLabelText", "Error. Please try again." + msUsername + msCurrentPassword + mstaff.getMsUsername() + mstaff.getMsPassword() + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", msUsername);
                request.getRequestDispatcher("EditMStaffProfile.jsp").include(request, response);
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
