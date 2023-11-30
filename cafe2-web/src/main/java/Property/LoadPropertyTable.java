package Property;

import Property.Property;
import Property.PropertyFacade;
import Seller.Seller;
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
    private PropertyFacade pf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession s = request.getSession();
            Seller seller = (Seller)s.getAttribute("seller");
            List<Property> prop = pf.findAll();

            for (Property property : prop) {
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
