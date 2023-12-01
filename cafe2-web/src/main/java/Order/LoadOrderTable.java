package Order;

import sOrder.SOrder;
import sOrder.SOrderFacade;
import Menu.Menu;
import Menu.MenuFacade;
import SStaff.SStaff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoadMenuTable", urlPatterns = {"/LoadMenuTable"})
public class LoadOrderTable extends HttpServlet {

    @EJB
    SOrderFacade of;
    @EJB
    MenuFacade mf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession s = request.getSession();
            SStaff sstaff = (SStaff) s.getAttribute("sstaff");

            List<SOrder> order = of.findAll();
            List<Menu> menu = mf.findAll();

            for (SOrder ord : order) {
                for (Menu m : menu) {
                    if (m.getMenuID() == ord.getMenuID() && sstaff.getSsUsername().equals(m.getSsUsername())) {
                        out.println("<tr>");
                        out.println("<td>" + ord.getOrderID() + "</td>");
                        out.println("<td>" + ord.getMenuID() + "</td>");
                        out.println("<td>" + ord.getMenuName() + "</td>");
                        out.println("<td>" + ord.getPrice() + "</td>");
                        out.println("<td>" + ord.getQuantity() + "</td>");
                        out.println("<td>" + ord.getcEmail() + "</td>");
                        out.println("<td>" + ord.getSsUsername() + "</td>");
                        out.println("</tr>");
                    }
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
