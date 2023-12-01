/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import SStaff.SStaff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DeleteMenu", urlPatterns = {"/DeleteMenu"})
public class DeleteMenu extends HttpServlet {

    @EJB
    private MenuFacade cf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession s = request.getSession();
        SStaff sstaff = (SStaff) s.getAttribute("sstaff");
        Menu menu = new Menu();

        try (PrintWriter out = response.getWriter()) {

            try {
                int menuID = Integer.parseInt(request.getParameter("menuID"));

                menu.setMenuID(menuID);
                cf.remove(menu);

                String c = "green";
                request.setAttribute("messageColor", c);
                request.setAttribute("messageLabelText", "Property Successfully Deleted!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                request.getRequestDispatcher("deleteMenu.jsp").include(request, response);
            } catch (ServletException | IOException | NumberFormatException e) {
                request.setAttribute("messageLabelText", "Invalid Property Details, Please Try Again!" + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                request.getRequestDispatcher("deleteMenu.jsp").include(request, response);
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
