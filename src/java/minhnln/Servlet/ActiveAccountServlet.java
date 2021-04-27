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
import minhnln.DAO.RegistrationDAO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "ActiveAccountServlet", urlPatterns = {"/ActiveAccountServlet"})
public class ActiveAccountServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String ERROR_PAGE = "error.jsp";
    private final String NOTACTIVATED_PAGE = "notactivated.jsp";

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
            RegistrationDAO Rdao = new RegistrationDAO();
            String email = request.getParameter("txtEmail");
            String activecode = request.getParameter("txtActiveCode");
            if (Rdao.checkActiveCode(email, activecode)) {
                url = LOGIN_PAGE;
                request.setAttribute("EMAIL", email);
            }else{
                url = NOTACTIVATED_PAGE;
                request.setAttribute("ERROR", "WRONG CODE!!!");
            }
        } catch (Exception e) {
            log("ERROR at ActiveCodeServlet: " + e.getMessage());
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
