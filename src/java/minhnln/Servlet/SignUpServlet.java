/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnln.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.DAO.RegistrationDAO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final String SIGNUP_PAGE = "signup.jsp";

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
        boolean error = false;
        try {
            RegistrationDAO dao = new RegistrationDAO();
            boolean valid = false;
            //Xử lý thông tin nhập vào
            String email = request.getParameter("txtEmail");
            String phone = request.getParameter("txtPhone");
            String name = request.getParameter("txtName");
            String address = request.getParameter("txtAddress");
            String password = request.getParameter("txtPassword");
            String confirmpassword = request.getParameter("txtConfirmPassword");
            Date createDate = (Date) Calendar.getInstance().getTime();
            java.sql.Date createDateSQL = new java.sql.Date(createDate.getTime());
            if (!email.matches("\\w+[@]\\w+[.][a-zA-Z]{2,4}[.]?[a-zA-Z]{0,4}$")) {
                request.setAttribute("ERROR", "Wrong input Email!!!");
                url = SIGNUP_PAGE;
                valid = true;
            }
            if (email.equals(dao.getEmailinDBbyEmail(email))) {
                request.setAttribute("ERROR", "This is mail is already in DataBase");
                url = SIGNUP_PAGE;
                valid = true;
            }
            if (!password.equals(confirmpassword)) {
                request.setAttribute("ERROR", "Confirm Password must be exactly like Password!");
                url = SIGNUP_PAGE;
                valid = true;
            }
            if (valid) {
                error = true;
                url = "signup.jsp?txtEmail=" + email + "&txtPhone=" + phone + "&txtName=" + name + "&txtAddress=" + address;
            } else {
                if (dao.createAccount(email, phone, name, address, createDateSQL, password)) {
                    url = LOGIN_PAGE;
                }
            }
        } catch (Exception e) {
            log("Error at SignUpServlet: " + e.getMessage());
        } finally {
            if (error) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect(url);
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
