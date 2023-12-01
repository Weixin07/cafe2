package Receipt;

import Customer.Customer;
import sOrder.SOrder;
import sOrder.SOrderFacade;
import SStaff.SStaff;
import MStaff.MStaff;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoadReceiptTable", urlPatterns = {"/LoadReceiptTable"})
public class LoadReceiptTable extends HttpServlet {

    @EJB
    ReceiptFacade rf;
    @EJB
    SOrderFacade of;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        SStaff sstaff = (SStaff) s.getAttribute("sstaff");
        Customer customer = (Customer) s.getAttribute("user");
        MStaff mstaff = (MStaff) s.getAttribute("mstaff");

        List<Receipt> receipt = rf.findAll();
        List<SOrder> order = of.findAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        try (PrintWriter out = response.getWriter()) {
            if (customer != null) {
                for (Receipt rec : receipt) {
                    for (SOrder ord : order) {
                        if (rec.getOrderID() == ord.getOrderID()) {
                            out.println("<tr>");
                            out.println("<td>" + rec.getReceiptID() + "</td>");
                            out.println("<td>" + rec.getrFeedback() + "</td>");
                            out.println("<td>" + rec.getrRating() + "</td>");
                            out.println("<td>" + sdf.format(rec.getrDateOfSale()) + "</td>");
                            out.println("<td>" + rec.getOrderID() + "</td>");
                            out.println("</tr>");
                        }
                    }
                }
            }
            if (sstaff != null) {
                for (Receipt rec : receipt) {
                    for (SOrder ord : order) {
                        if (rec.getOrderID() == ord.getOrderID()) {
                            out.println("<tr>");
                            out.println("<td>" + rec.getReceiptID() + "</td>");
                            out.println("<td>" + rec.getrFeedback() + "</td>");
                            out.println("<td>" + rec.getrRating() + "</td>");
                            out.println("<td>" + sdf.format(rec.getrDateOfSale()) + "</td>");
                            out.println("<td>" + rec.getOrderID() + "</td>");
                            out.println("</tr>");
                        }
                    }
                }
            }
            if (mstaff != null) {
                for (Receipt rec : receipt) {
                    out.println("<tr>");
                    out.println("<td>" + rec.getReceiptID() + "</td>");
                    out.println("<td>" + rec.getrFeedback() + "</td>");
                    out.println("<td>" + rec.getrRating() + "</td>");
                    out.println("<td>" + sdf.format(rec.getrDateOfSale()) + "</td>");
                    out.println("<td>" + rec.getOrderID() + "</td>");
                    out.println("</tr>");
                }
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
