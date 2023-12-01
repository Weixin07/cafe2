/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Menu.MenuFacade;
import Menu.Menu;
import SStaff.SStaff;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet(name = "EditProperty", urlPatterns = {"/EditProperty"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditProperty extends HttpServlet {

    @EJB
    private MenuFacade pf;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        HttpSession s = request.getSession();
        SStaff seller = (SStaff)s.getAttribute("seller");
        Menu property = new Menu();
            
        try{
        
        int pId = Integer.parseInt(request.getParameter("pId"));
        String pSaleType = request.getParameter("pSaleType");
        int pPrice = Integer.parseInt(request.getParameter("pPrice"));
        String pAddress = request.getParameter("pAddress");
        String pDescription = request.getParameter("pDescription");
        String pType = request.getParameter("pType");
        int pSize = Integer.parseInt(request.getParameter("pSize"));
        String pFurnish = request.getParameter("pFurnish");
        String pBuild = request.getParameter("pBuild");
        
            
                if(pPrice<10000){
                    request.setAttribute("messageLabelText", "UNREAL ESTATE Only lists Properties valued 10,000MYR or above");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", seller.getsName());
                    request.getRequestDispatcher("editProperty.jsp").include(request, response);
                    
                }else if(pAddress.length()<10){
                    request.setAttribute("messageLabelText", "Make sure your Address is at least 10 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", seller.getsName());
                    request.getRequestDispatcher("editProperty.jsp").include(request, response);
                    
                }else if(pType.equals("Property Type")){
                    request.setAttribute("messageLabelText", "Make sure you have chosen a Property Type");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", seller.getsName());
                    request.getRequestDispatcher("editProperty.jsp").include(request, response);
                
                }else if(pSize<100){
                    request.setAttribute("messageLabelText", "UNREAL ESTATE Only lists Properties with a minimum Size of 100sqft");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", seller.getsName());
                    request.getRequestDispatcher("editProperty.jsp").include(request, response);
                }else if(pFurnish.equals("Furnish Quality")){
                    request.setAttribute("messageLabelText", "Make sure your Address is at least 16 characters");
                    request.setAttribute("hideMessageLabel", false);
                    request.setAttribute("uNameLabelText", seller.getsName());
                    request.getRequestDispatcher("editProperty.jsp").include(request, response);
                }else{
           
            property.setId(pId);
            property.setpSaleType(pSaleType);
            property.setpPrice(pPrice);
            property.setpAddress(pAddress);
            property.setpDescription(pDescription);
            property.setpType(pType);
            property.setpSize(pSize);
            property.setpFurnish(pFurnish);
            property.setpBuild(pBuild);
            property.setpSeller(seller.getsEmail());
            // Save the property details in the database
            pf.edit(property);
                    
      
                String c ="green";
                request.setAttribute("messageColor", c);
                request.setAttribute("messageLabelText", "Property Successfully Edited!");
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", seller.getsName());
                request.getRequestDispatcher("editProperty.jsp").include(request, response);
                }
            }catch(Exception e){
                
                request.setAttribute("messageLabelText","Invalid Property Details, Please Try Again!" + e);
                request.setAttribute("hideMessageLabel", false);
                request.setAttribute("uNameLabelText", seller.getsName());
                request.getRequestDispatcher("editProperty.jsp").include(request, response);
                
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
