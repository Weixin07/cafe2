package SAdmin;

import sAdmin.SAdmin;
import sAdmin.SAdminFacade;
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

@WebServlet(name = "SearchBuyer", urlPatterns = {"/SearchBuyer"})
public class SearchBuyer extends HttpServlet {
    
    @EJB
    SAdminFacade bf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
     
        HttpSession s = request.getSession();
        MStaff staff = (MStaff)s.getAttribute("staff");
        String searchStaff = request.getParameter("searchBuyer");
        
        try{
            

            List<SAdmin> buyerList = bf.findAll();
            List<SAdmin> filteredBuyers = new ArrayList<>();
            boolean pFound = false;
            
            for(SAdmin buy : buyerList){
                if (buy.getbUsername().toLowerCase().contains(searchStaff.toLowerCase())) {
                    filteredBuyers.add(buy);
                    pFound = true;
                }
            }
            
            if(pFound==false){
                request.setAttribute("messageLabelText","No Staff Found, Please Try Again!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", staff.getStName());
                request.getRequestDispatcher("searchBuyer.jsp").include(request, response);
            }else{
           
            request.setAttribute("filteredBuyers", filteredBuyers);
            request.setAttribute("uNameLabelText", (staff.getStName()));
            request.setAttribute("hideBuyerTableLabel", true);}
           
            request.getRequestDispatcher("searchBuyer.jsp").forward(request, response);
            }catch(Exception e){
                
                request.setAttribute("messageLabelText","Error fetching Staff, Please Try Again!" + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", staff.getStName());
                request.setAttribute("hideBuyerTableLabel", true);
                
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
