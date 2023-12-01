package Receipt;

import MStaff.MStaff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SearchReceipt", urlPatterns = {"/SearchReceipt"})
public class SearchReceipt extends HttpServlet {

    @EJB
    ReceiptFacade rf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession s = request.getSession();
        MStaff mstaff = (MStaff) s.getAttribute("mstaff");

        try (PrintWriter out = response.getWriter()) {
            try {
                String searchReceipt = request.getParameter("searchReceipt");
                // Retrieve the filtered data based on the search
                List<Receipt> receiptList = rf.findAll();
                List<Receipt> filteredReceipt = new ArrayList<>();
                boolean rFound = false;

                for (Receipt rL : receiptList) {
                    if (rL.getReceiptID().toString().equals(searchReceipt)) {
                        filteredReceipt.add(rL);
                        rFound = true;
                    }
                }
                if (rFound == false) {
                    request.setAttribute("messageLabelText", "Receipt does not exist, please try again.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                    request.getRequestDispatcher("feedbackAnalysis.jsp").include(request, response);
                } else {
                    // Set the filtered data as an attribute
                    request.setAttribute("filteredReceipt", filteredReceipt);
                    request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                    request.setAttribute("hideReceiptTableLabel", true);
                }
                // Forward the request to the jsp page
                request.getRequestDispatcher("feedbackAnalysis.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                request.setAttribute("messageLabelText", "Error. Please try again." + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", mstaff.getMsUsername());
                request.setAttribute("hideReceiptTableLabel", true);
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
