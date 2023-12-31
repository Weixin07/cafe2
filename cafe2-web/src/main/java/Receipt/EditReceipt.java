/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Receipt;

import Customer.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;

@WebServlet(name = "EditReceipt", urlPatterns = {"/EditReceipt"})
public class EditReceipt extends HttpServlet {
    @EJB
    ReceiptFacade rf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession s = request.getSession();
            Customer customer = (Customer) s.getAttribute("customer");

            try {
                int receiptID = Integer.parseInt(request.getParameter("receiptID"));
                String rFeedback = request.getParameter("rFeedback");
                int rRating = Integer.parseInt(request.getParameter("rRating"));
                String rDateOfSale = request.getParameter("rDateOfSale");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
                Date rDOS = sdf.parse(rDateOfSale);

                int OrderID = Integer.parseInt(request.getParameter("OrderID"));
                if (rFeedback.length() < 0) {
                    request.setAttribute("messageLabelText", "Please type in the feedback.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", customer.getcEmail());
                    request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
                } else if (rRating < 1 || rRating > 5) {
                    request.setAttribute("messageLabelText", "Rate from 1 to 5 only.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", customer.getcEmail());
                    request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
                } else {
                    Receipt rec = new Receipt();
                    rec.setReceiptID(receiptID);
                    rec.setrFeedback(rFeedback);
                    rec.setrRating(rRating);
                    rec.setrDateOfSale(rDOS);
                    rec.setOrderID(OrderID);
                    // Save the receipt details in the database
                    rf.edit(rec);

                    String c = "green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Thank you for the Feedback!");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", customer.getcEmail());
                    request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
                }
            } catch (ServletException | IOException | NumberFormatException | ParseException e) {
                request.setAttribute("messageLabelText", "Error, please try again.");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", customer.getcEmail());
                request.getRequestDispatcher("viewPurchaseHistory.jsp").include(request, response);
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
