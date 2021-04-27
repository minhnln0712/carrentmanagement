/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.DAO.RentalDAO;
import minhnln.DTO.RentalDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "SearchHistoryServlet", urlPatterns = {"/SearchHistoryServlet"})
public class SearchHistoryServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String HISTORY_PAGE = "history.jsp";

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
            String role = (String) session.getAttribute("ROLE");
            if (role != null) {
                RentalDAO Rdao = new RentalDAO();
                String name = "%";
                if (!request.getParameter("txtNameSearch").isEmpty() && request.getParameter("txtNameSearch") != null) {
                    name = request.getParameter("txtNameSearch");
                    session.setAttribute("NAMESEARCH", name);
                }
                String rentaldate = "1900-01-01";
                if (!request.getParameter("txtRentDate").isEmpty()) {
                    rentaldate = request.getParameter("RENTDATE");
                    request.setAttribute("RENTDATE", rentaldate);
                }
                String returndate = "3000-12-31";
                if (!request.getParameter("txtReturnDate").isEmpty()) {
                    returndate = request.getParameter("txtReturnDate");
                    request.setAttribute("RETURNDATE", returndate);
                }
                if (role.equals("admin")) {
                    List<RentalDTO> list = Rdao.searchInRental(name, rentaldate, returndate);
                    session.setAttribute("RENTALLIST", list);
                    url = HISTORY_PAGE;
                } else {
                    List<RentalDTO> list = Rdao.searchInRental((String) session.getAttribute("EMAIL"), rentaldate, returndate);
                    session.setAttribute("RENTALLIST", list);
                    url = HISTORY_PAGE;
                }
            }

        } catch (Exception e) {
            log("Error at SearchHistoryServlet: " + e.getMessage());
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
