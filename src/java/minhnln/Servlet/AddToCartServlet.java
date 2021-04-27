/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.DAO.CarDAO;
import minhnln.DAO.RentalDetailDAO;
import minhnln.DTO.CarDTO;
import minhnln.DTO.CartDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    final private String DISPLAY_CAR_SERVLET = "DisplayCarServlet";

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
        String carDetailID = request.getParameter("txtCarDetailID");
        String rentDate = request.getParameter("txtRentDate");
        String returnDate = request.getParameter("txtReturnDate");
        try {
            url = DISPLAY_CAR_SERVLET;
            HttpSession session = request.getSession();
            if (session.getAttribute("ROLE").equals("user")) {
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartDTO();
                }
                CarDAO Cdao = new CarDAO();
                List<CarDTO> list = Cdao.getAllCarDetail();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCarDetailID().equals(carDetailID)) {
                        cart.addToCart(list.get(i), rentDate, returnDate, Cdao.getAllCarDetailWithID(list.get(i).getCarID()).size());
                        break;
                    }
                }
                session.setAttribute("TOTAL", cart.getTotalMoney());
                session.setAttribute("CART", cart);
            }
        } catch (Exception e) {
            log("Error at AddToCartServlet: " + e.getMessage());
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
