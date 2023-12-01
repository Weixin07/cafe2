package Menu;

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

@WebServlet(name = "LoadPropertyTable", urlPatterns = {"/LoadPropertyTable"})
public class LoadPropertyTable extends HttpServlet {

    @EJB
    private MenuFacade pf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession s = request.getSession();
            SStaff seller = (SStaff)s.getAttribute("seller");
            List<Menu> prop = pf.findAll();

            for (Menu property : prop) {
               if (property.getpSeller().equals(seller.getsEmail())){ 
                out.println("<tr>");
                out.println("<td>" + property.getpPrice() + "</td>");
                out.println("<td>" + property.getpAddress() + "</td>");
                out.println("<td>" + property.getpType() + "</td>");
                out.println("<td>" + property.getpSize() + "</td>");
                out.println("<td>" + property.getpFurnish() + "</td>");
                out.println("<td>" + property.getpBuild() + "</td>");
                out.println("</tr>");
               }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
