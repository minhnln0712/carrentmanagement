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
import minhnln.DAO.RegistrationDAO;
import minhnln.DTO.RegistrationDTO;
import minhnln.utils.VerifyRecaptcha;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    final private String DISPLAY_CAR_SERVLET = "DisplayCarServlet";
    final private String ERROR_PAGE = "error.jsp";
    final private String LOGIN_PAGE = "login.jsp";
    final private String VERIFY_PAGE = "verify.jsp";

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
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        try {
            //Gọi Dao
            RegistrationDAO dao = new RegistrationDAO();
            //Thông tin nhận được
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            //Xử lý thông tin
            HttpSession session = request.getSession();
            if (!email.matches("\\w+[@]\\w+[.][a-zA-Z]{2,4}[.]?[a-zA-Z]{0,4}$")) {
                request.setAttribute("ERROR", "Wrong input Email!!!");
                url = "login.jsp?txtEmail=" + email;
                error = true;
                return;
            }
            if (!password.equals(dao.getPasswordbyEmail(email))) {
                request.setAttribute("ERROR", "Wrong email or password!!!");
                url = "login.jsp?txtEmail=" + email;
                error = true;
                return;
            }
            if (verify) {
                if (dao.loginToActivation(email, password)) {
                    url = VERIFY_PAGE;
                    request.setAttribute("EMAIL", email);
                    error = true;
                    return;
                }
                RegistrationDTO dto = dao.login(email, password);
                if (dto != null) {
                    url = DISPLAY_CAR_SERVLET;
                    session.setAttribute("EMAIL", email);
                    session.setAttribute("NAME", dto.getName());
                    session.setAttribute("ROLE", dto.getRole());
                } else {
                    url = LOGIN_PAGE;
                    request.setAttribute("ERROR", "Wrong email or password!!!");
                    url = "login.jsp?txtEmail=" + email;
                    error = true;
                }
            } else {
                url = LOGIN_PAGE;
                request.setAttribute("ERROR", "Please,Click the reCaptcha!");
                url = "login.jsp?txtEmail=" + email;
                error = true;
            }
        } catch (Exception e) {
            log("Error at Login Servlet: " + e.getMessage());
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
