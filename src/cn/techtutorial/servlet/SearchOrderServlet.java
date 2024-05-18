package cn.techtutorial.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.OrderDao;
import cn.techtutorial.model.Order;

@WebServlet("/SearchOrderServlet")
public class SearchOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String searchTerm = request.getParameter("search");
            OrderDao orderDao = new OrderDao(DbCon.getConnection());
            List<Order> searchResults;

            if (searchTerm != null && !searchTerm.isEmpty()) {
                // Thực hiện tìm kiếm và lưu kết quả vào attribute
                searchResults = orderDao.searchOrder(searchTerm);
                request.setAttribute("SEARCH_RESULTS", searchResults);
            } else {
                // Nếu không có dữ liệu tìm kiếm, lấy toàn bộ đơn hàng
                List<Order> orders = orderDao.getAllOrders();
                request.setAttribute("ORDERS_LIST", orders);
            }

            // Forward request về trang hiện tại để hiển thị kết quả
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin-orders.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred. Please check the server logs.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
