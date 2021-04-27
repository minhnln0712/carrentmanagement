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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnln.DAO.CarDAO;
import minhnln.DAO.CategoryDAO;
import minhnln.DAO.DiscountDAO;
import minhnln.DTO.CarDTO;
import minhnln.DTO.CategoryDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "DisplayCarServlet", urlPatterns = {"/DisplayCarServlet"})
public class DisplayCarServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.jsp";
    private final String SEARCH_PAGE = "search.jsp";

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
        final int pagingSize = 6; //tổng xe của 1 trang
        int SumofCar;// tổng số lượng xe
        int NumofPage;//Số trang sẽ được tạo
        int pageNo = 1;//Mặc định sẽ là trang đầu tiên (pageNo=1)
        try {
            CarDAO Cdao = new CarDAO();
            DiscountDAO Ddao = new DiscountDAO();
            CategoryDAO Catedao = new CategoryDAO();
            //Về số lượng trang~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            if (request.getParameter("pageNo") != null) {
                pageNo = Integer.parseInt(request.getParameter("pageNo"));
            }
            SumofCar = Cdao.getSumOfCar();
            NumofPage = SumofCar / pagingSize;
            if (SumofCar > pagingSize * NumofPage) {//Nếu tổng sản phẩm lớn hơn tổng số lượng sản phẩm trong các trang
                NumofPage += 1;
            }
            //Về hiển thị trên trang
            List<CarDTO> Clist = Cdao.getCarInforWithPaging(pageNo, pagingSize);
            List<CategoryDTO> Catelist = Catedao.getAllCategory();
            url = SEARCH_PAGE;
            Date date = (Date) Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String datenow = df.format(date);
            //Lưu trữ trong server
            HttpSession session = request.getSession();
            session.setAttribute("DISCOUNT", Ddao.getAllDiscount());
            session.setAttribute("DATENOW", datenow);
            request.setAttribute("LISTCAR", Clist);
            request.setAttribute("LISTCATE", Catelist);
            request.setAttribute("MAXPAGE", NumofPage);
        } catch (Exception e) {
            log("Error at DisplayCarServlet: " + e.getMessage());
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
