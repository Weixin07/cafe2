package Menu;

import SStaff.SStaff;
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

@WebServlet(name = "SearchMenu", urlPatterns = {"/SearchMenu"})
public class SearchMenu extends HttpServlet {
    @EJB
    MenuFacade mf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String ItemName = request.getParameter("ItemName");
            HttpSession s = request.getSession();
            SStaff sstaff = (SStaff) s.getAttribute("sstaff");

            try {

                // Retrieve the filtered property data based on the search address from the database
                List<Menu> menuList = mf.findAll();
                List<Menu> filteredProperties = new ArrayList<>();
                String menuName = null;
                boolean mFound = false;

                for (Menu menu : menuList) {
                    if (menu.getMenuName().toLowerCase().contains(ItemName.toLowerCase())) {
                        filteredProperties.add(menu);
                        mFound = true;
                    }
                    menuName = menu.getMenuName();
                }

                if (mFound == false) {
                    request.setAttribute("messageLabelText", "Menu item searched is not found.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                    request.setAttribute("hidePropertyTableLabel", true);
                    request.getRequestDispatcher("searchProperty.jsp").include(request, response);
                } else {
                    // Set the filtered data as an attribute
                    request.setAttribute("filteredProperties", filteredProperties);
                    request.setAttribute("uNameLabelText", (sstaff.getSsUsername()));
                    request.setAttribute("hidePropertyTableLabel", true);
                }
                // Forward the request to the jsp page
                request.getRequestDispatcher("searchMenu.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                request.setAttribute("messageLabelText", "An exception has occured, please try again." + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                request.setAttribute("hidePropertyTableLabel", true);
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
