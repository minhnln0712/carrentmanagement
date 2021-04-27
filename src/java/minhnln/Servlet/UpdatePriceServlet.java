/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.DTO.CartDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "UpdatePriceServlet", urlPatterns = {"/UpdatePriceServlet"})
public class UpdatePriceServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String CART_PAGE = "cart.jsp";

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
        String[] carDetailIDArray = request.getParameterValues("txtCarDetailID");
        String[] rentDateArray = request.getParameterValues("txtRentDate");
        String[] returnDateArray = request.getParameterValues("txtReturnDate");
        float discount = 0;
        if (request.getParameter("cbbDiscount") != null) {
            discount = Float.parseFloat(request.getParameter("cbbDiscount"));
        }
        try {
            url = CART_PAGE;
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            for (int i = 0; i < cart.getCart().size(); i++) {
                cart.changeInCart(carDetailIDArray[i], rentDateArray[i], returnDateArray[i]);
            }
            float totalMoney = cart.getTotalMoney();
            session.setAttribute("TOTAL", totalMoney - (totalMoney * discount / 100));
            session.setAttribute("CART", cart);
            request.setAttribute("DISCOUNT", discount);
        } catch (Exception e) {
            log("Error at UpdatePriceServlet:" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
