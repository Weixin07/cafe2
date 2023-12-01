package Order;

import Customer.Customer;
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
import sOrder.SOrder;
import sOrder.SOrderFacade;

@WebServlet(name = "CreateOrder", urlPatterns = {"/CreateOrder"})
public class CreateOrder extends HttpServlet {

    @EJB
    SOrderFacade of;
    @EJB
    MenuFacade mf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        Customer customer = (Customer) s.getAttribute("user");
        SStaff sstaff = (SStaff) s.getAttribute("sstaff");

        try (PrintWriter out = response.getWriter()) {
            try {
                int OrderID = of.count();
                int menuID = Integer.parseInt(request.getParameter("menuID"));
                String menuName = request.getParameter("menuName");
                float price = Float.parseFloat(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                if (quantity < 1) {
                    request.setAttribute("messageLabelText", "Food quantity is at least 1.");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                    request.getRequestDispatcher("createOrder.jsp").include(request, response);
                } else {
                    of.create(new SOrder(OrderID + 1, menuID, menuName, price, quantity, customer.getcEmail(), sstaff.getSsUsername()));

                    request.setAttribute("hideSMRELabel", true);
                    request.setAttribute("hideSAOLabel", true);
                    request.setAttribute("hideSEPHLabel", true);
                    request.setAttribute("hideEPLabel", false);
                    request.setAttribute("hideBVPHLabel", false);
                    request.setAttribute("hideSMBLabel", true);
                    request.setAttribute("hideSMSLabel", true);
                    request.setAttribute("hideSFBALabel", true);
                    request.setAttribute("hideAMSLabel", true);
                    request.setAttribute("hideLoginLabel", true);
                    request.setAttribute("uNameLabelText", customer.getcEmail());

                    List<Menu> menu = mf.findAll();
                    request.setAttribute("menu", menu);
                    request.getRequestDispatcher("menuListings.jsp").include(request, response);

                    out.println("<script>");
                    out.println("alert('Order has been sent successfully!');");
                    out.println("</script>");
                }

            } catch (ServletException | IOException | NumberFormatException e) {
                request.setAttribute("messageLabelText", "Invalid Property Details, Please Try Again!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", sstaff.getSsUsername());
                request.getRequestDispatcher("createProperty.jsp").include(request, response);
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
