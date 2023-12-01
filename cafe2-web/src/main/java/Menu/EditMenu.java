package Menu;

import SStaff.SStaff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "EditMenu", urlPatterns = {"/EditMenu"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)

public class EditMenu extends HttpServlet {
    @EJB
    private MenuFacade mf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        SStaff sstaff = (SStaff) s.getAttribute("sstaff");

        try (PrintWriter out = response.getWriter()) {
            try {
                String menuName = request.getParameter("menuName");
                float price = Float.parseFloat(request.getParameter("price"));

                if (price < 0.00) {
                    request.setAttribute("messageLabelText", "Price of items should be at least RM0.01");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                    request.getRequestDispatcher("createMenu.jsp").include(request, response);

                } else if (menuName.length() < 1) {
                    request.setAttribute("messageLabelText", "Name of item should be at least 1 character");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                    request.getRequestDispatcher("createMenu.jsp").include(request, response);

                } else {
                    int menuID = mf.count() + 1;
                    //saving the new object into database
                    mf.create(new Menu(menuID, menuName, price, sstaff.getSsUsername()));

                    String c = "green";
                    request.setAttribute("messageColor", c);
                    request.setAttribute("messageLabelText", "Item is added.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                    request.getRequestDispatcher("createMenu.jsp").include(request, response);
                }
            } catch (ServletException | IOException | NumberFormatException e) {

                request.setAttribute("messageLabelText", "Menu item is not edited, please try again.");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                request.getRequestDispatcher("createMenu.jsp").include(request, response);

            }
        }
    }

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
