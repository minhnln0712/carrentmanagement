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
import minhnln.DAO.CarDAO;
import minhnln.DAO.CategoryDAO;
import minhnln.DAO.RentalDetailDAO;
import minhnln.DTO.CarDTO;
import minhnln.DTO.CategoryDTO;

/**
 *
 * @author Welcome
 */
@WebServlet(name = "SearchCarServlet", urlPatterns = {"/SearchCarServlet"})
public class SearchCarServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        final int pagingSize = 6; //tổng xe của 1 trang
        int SumofCar;// tổng số lượng xe
        int NumofPage;//Số trang sẽ được tạo
        int pageNo = 1;//Mặc định sẽ là trang đầu tiên (pageNo=1)
        boolean getdate = false;
        try {
            HttpSession session = request.getSession();
            url = SEARCH_PAGE;
            CarDAO Cdao = new CarDAO();
            CategoryDAO Catedao = new CategoryDAO();
            RentalDetailDAO RDdao = new RentalDetailDAO();
            String keyword = "%";
            if (!request.getParameter("txtSearch").trim().isEmpty()) {
                keyword = request.getParameter("txtSearch");
                request.setAttribute("KEYWORD", keyword);
            }
            String cateID = "%";
            if (!request.getParameter("cbbCategory").isEmpty() && !request.getParameter("cbbCategory").equals("%")) {
                cateID = request.getParameter("cbbCategory");
                request.setAttribute("CATEID", cateID);
            }
            String quantity = "%";
            if (!request.getParameter("txtQuantity").equals("0") && request.getParameter("txtQuantity") != null && !request.getParameter("txtQuantity").isEmpty()) {
                quantity = request.getParameter("txtQuantity");
                request.setAttribute("QUANTITY", quantity);
            }
            String rentdate = "1900-01-01";
            if (!request.getParameter("txtRentDate").isEmpty()) {
                rentdate = request.getParameter("txtRentDate");
                request.setAttribute("RENTDATE", rentdate);
                getdate = true;
            }
            String returndate = "3000-12-31";
            if (!request.getParameter("txtReturnDate").isEmpty()) {
                returndate = request.getParameter("txtReturnDate");
                request.setAttribute("RETURNDATE", returndate);
                getdate = true;
            }
            //Về số lượng trang~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            if (request.getParameter("pageNo") != null) {
                pageNo = Integer.parseInt(request.getParameter("pageNo"));
            }
            SumofCar = Cdao.getSumOfCarAfterSearch(keyword, cateID, quantity);
            NumofPage = SumofCar / pagingSize;
            if (SumofCar > pagingSize * NumofPage) {//Nếu tổng sản phẩm lớn hơn tổng số lượng sản phẩm trong các trang
                NumofPage += 1;
            }
            //Về hiển thị trên trang
            List<CarDTO> Clist = Cdao.searchCarWithPaging(pageNo, pagingSize, keyword, cateID, quantity);
            for (int i = 0; i < Clist.size(); i++) {
                if (getdate) {
                    Clist.get(i).setQuantity(Clist.get(i).getQuantity() - RDdao.getNumOfCarInOrder(Clist.get(i).getCarID(), rentdate, returndate));
                }
            }
            List< CategoryDTO> Catelist = Catedao.getAllCategory();
            url = SEARCH_PAGE;
            System.out.println(keyword);
            System.out.println(cateID);
            System.out.println(quantity);
            //Lưu trữ trong server
            request.setAttribute("LISTCAR", Clist);
            request.setAttribute("LISTCATE", Catelist);
            request.setAttribute("MAXPAGE", NumofPage);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchCarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
