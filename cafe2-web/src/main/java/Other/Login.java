package Other;

import Customer.Customer;
import Customer.CustomerFacade;
import java.io.IOException;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import SStaff.SStaff;
import SStaff.SStaffFacade;
import MStaff.MStaff;
import MStaff.MStaffFacade;
import jakarta.servlet.http.HttpSession;
import sAdmin.SAdmin;
import sAdmin.SAdminFacade;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    CustomerFacade cf;
    @EJB
    SStaffFacade ssf;
    @EJB
    MStaffFacade msf;
    @EJB
    SAdminFacade af;

    SStaff ssFound;
    Customer cFound;
    MStaff msFound;
    SAdmin aFound;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession s = request.getSession();

        String eMail = request.getParameter("eMail");
        String pass = request.getParameter("pass");

        cFound = cf.find(eMail);
        ssFound = ssf.find(eMail);
        msFound = msf.find(eMail);
        aFound = af.find(eMail);

        s.setAttribute("customer", cFound);
        s.setAttribute("sstaff", ssFound);
        s.setAttribute("mstaff", msFound);
        s.setAttribute("admin", aFound);

        switch (getType(eMail, pass)) {
            case "customer" -> {
                request.setAttribute("hideSMRELabel", false);
                request.setAttribute("hideSAOLabel", false);
                request.setAttribute("hideSEPHLabel", false);
                request.setAttribute("hideEPLabel", false);
                request.setAttribute("hideBVPHLabel", true);
                request.setAttribute("hideSMBLabel", true);
                request.setAttribute("hideSMSLabel", true);
                request.setAttribute("hideSFBALabel", true);
                request.setAttribute("hideAMSLabel", true);
                request.setAttribute("uNameLabelText", cFound.getcName());
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("hideNameLabel", false);
                request.setAttribute("hidePropertyListingsLabel", false);
                request.getRequestDispatcher("home.jsp").include(request, response);
            }
            case "sstaff" -> {
                request.setAttribute("hideSMRELabel", true);
                request.setAttribute("hideSAOLabel", true);
                request.setAttribute("hideSEPHLabel", true);
                request.setAttribute("hideEPLabel", false);
                request.setAttribute("hideBVPHLabel", false);
                request.setAttribute("hideSMBLabel", true);
                request.setAttribute("hideSMSLabel", true);
                request.setAttribute("hideSFBALabel", true);
                request.setAttribute("hideAMSLabel", true);
                request.setAttribute("uNameLabelText", ssFound.getSsUsername());
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("hideNameLabel", false);
                request.setAttribute("hidePropertyListingsLabel", false);
                request.getRequestDispatcher("home.jsp").include(request, response);
            }
            case "mstaff" -> {
                request.setAttribute("hideSMRELabel", true);
                request.setAttribute("hideSAOLabel", true);
                request.setAttribute("hideSEPHLabel", true);
                request.setAttribute("hideEPLabel", false);
                request.setAttribute("hideBVPHLabel", true);
                request.setAttribute("hideSMBLabel", false);
                request.setAttribute("hideSMSLabel", false);
                request.setAttribute("hideSFBALabel", false);
                request.setAttribute("hideAMSLabel", true);
                request.setAttribute("uNameLabelText", msFound.getMsUsername());
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("hideNameLabel", false);
                request.setAttribute("hidePropertyListingsLabel", false);
                request.getRequestDispatcher("home.jsp").include(request, response);
            }
            case "admin" -> {
                request.setAttribute("hideSMRELabel", true);
                request.setAttribute("hideSAOLabel", true);
                request.setAttribute("hideSEPHLabel", true);
                request.setAttribute("hideEPLabel", true);
                request.setAttribute("hideBVPHLabel", true);
                request.setAttribute("hideSMBLabel", true);
                request.setAttribute("hideSMSLabel", true);
                request.setAttribute("hideSFBALabel", true);
                request.setAttribute("hideAMSLabel", false);
                request.setAttribute("uNameLabelText", aFound.getaUsername());
                request.setAttribute("hideLoginLabel", true);
                request.setAttribute("hideNameLabel", false);
                request.setAttribute("hidePropertyListingsLabel", false);
                request.getRequestDispatcher("home.jsp").include(request, response);
            }
            default -> {
                request.setAttribute("messageLabelText", "Your username or password is incorrect, please try again.");
                request.setAttribute("hideMessageLabel", false);
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        }

    }

    private String getType(String email, String password) {
        if (cFound != null && cFound.getcEmail().equals(email) && cFound.getcPassword().equals(password) ) {
            return "customer";
        } else if (ssFound != null && ssFound.getSsUsername().equals(email) && ssFound.getSsPassword().equals(password)) {
            return "sstaff";
        } else if (msFound != null && msFound.getMsUsername().equals(email) && msFound.getMsPassword().equals(password)) {
            return "mstaff";
        } else if (aFound != null && aFound.getaUsername().equals(email) && aFound.getaPassword().equals(password)) {
            return "admin";
        } else {
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
