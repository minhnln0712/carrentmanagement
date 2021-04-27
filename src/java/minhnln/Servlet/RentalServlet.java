/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.DAO.DiscountDAO;
import minhnln.DAO.RentalDAO;
import minhnln.DAO.RentalDetailDAO;
import minhnln.DTO.CarDTO;
import minhnln.DTO.CartDTO;
import minhnln.DTO.DiscountDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "RentalServlet", urlPatterns = {"/RentalServlet"})
public class RentalServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String DISPLAY_CAR_SERVLET = "DisplayCarServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        try {
            HttpSession session = request.getSession();
            RentalDAO Rdao = new RentalDAO();
            RentalDetailDAO RDdao = new RentalDetailDAO();
            DiscountDAO Ddao = new DiscountDAO();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            String RentalID = "";
            if (Rdao.getLastRentalID().isEmpty()) {
                RentalID = "R-0000001";
            } else {
                RentalID = Rdao.getLastRentalID();
                String[] RentalIDArray = RentalID.split("-");
                RentalID = "R-" + String.format("%07d", (Integer.parseInt(RentalIDArray[1]) + 1));
            }
            String email = (String) session.getAttribute("EMAIL");
            Date createDate = (Date) Calendar.getInstance().getTime();
            java.sql.Date createDateSQL = new java.sql.Date(createDate.getTime());
            String DiscountID = request.getParameter("cbbDiscount");
            List<DiscountDTO> Dlist = Ddao.getAllDiscount();
            float DiscountPercent = 0;
            for (int i = 0; i < Dlist.size(); i++) {
                if (Dlist.get(i).getDiscountID().equals(DiscountID)) {
                    DiscountPercent = Dlist.get(i).getDiscountPercent();
                }
            }
            float totalPrice = cart.getTotalMoney() - (cart.getTotalMoney() * DiscountPercent / 100);
            if (Rdao.createRental(RentalID, email, totalPrice, createDateSQL, DiscountID)) {
                for (CarDTO car : cart.getCart().values()) {
                    String RentalDetailID = "";
                    if (RDdao.getLastRentalDetailID(RentalID).isEmpty()) {
                        RentalDetailID = RentalID + "-001";
                    } else {
                        RentalDetailID = RDdao.getLastRentalDetailID(RentalID);
                        String[] RentalDetailIDArray = RentalDetailID.split("-");
                        RentalDetailID = RentalID + "-" + String.format("%03d", (Integer.parseInt(RentalDetailIDArray[2]) + 1));
                    }
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date rentDay = df.parse(car.getRentDate());
                    java.sql.Date rentDateSQL = new java.sql.Date(rentDay.getTime());
                    Date returnDay = df.parse(car.getReturnDate());
                    java.sql.Date returnDateSQL = new java.sql.Date(returnDay.getTime());
                    String carID = car.getCarID();
                    String carDetailID = car.getCarDetailID();
                    String LicenceID = car.getLicenseID();
                    if (RDdao.createRentalDetail(RentalDetailID, RentalID, carID, rentDateSQL, returnDateSQL, carDetailID, LicenceID)) {
                        url = DISPLAY_CAR_SERVLET;
                    }
                }
            }
            cart.getCart().clear();
            session.removeAttribute("CART");
            session.removeAttribute("TOTAL");
        } catch (Exception e) {
            log("Error at RentalServlet:" + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
