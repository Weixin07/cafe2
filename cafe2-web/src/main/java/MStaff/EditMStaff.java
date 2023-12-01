package MStaff;

import sAdmin.SAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditMStaff", urlPatterns = {"/EditMStaff"})
public class EditMStaff extends HttpServlet {

    @EJB
    MStaffFacade msf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession s = request.getSession();
            SAdmin admin = (SAdmin) s.getAttribute("admin");
            MStaff mstaff = new MStaff();

            try {
                String msUsername = request.getParameter("msUsername");
                String msPassword = request.getParameter("msPassword");

                if (msUsername.length() < 1) {
                    request.setAttribute("messageLabelText", "Please input the username.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", admin.getaUsername());
                    request.getRequestDispatcher("editMStaff.jsp").include(request, response);

                } else if (msPassword.length() < 1) {
                    request.setAttribute("messageLabelText", "Please input the password.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", admin.getaUsername());
                    request.getRequestDispatcher("editMStaff.jsp").include(request, response);
                } else {
                    // Save details in the database
                    mstaff.setMsUsername(msUsername);
                    mstaff.setMsPassword(msPassword);
                    msf.edit(mstaff);

                    String c = "green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Managing staff's detailed successfully edited.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", admin.getaUsername());
                    request.getRequestDispatcher("editMStaff.jsp").include(request, response);
                }
            } catch (ServletException | IOException e) {

                request.setAttribute("messageLabelText", "Error. Please try again." + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", admin.getaUsername());
                request.getRequestDispatcher("editMStaff.jsp").include(request, response);

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
