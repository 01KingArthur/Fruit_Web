package cn.techtutorial.servlet;

import cn.techtutorial.connection.DbCon;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import cn.techtutorial.model.OrderDetail;
import cn.techtutorial.dao.OrderDetailDao;
import cn.techtutorial.dao.OrderDao;
import java.util.List;
import cn.techtutorial.model.User;


@WebServlet("/order-details")
public class DetailOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DetailOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy orderId từ request
            int orderId = Integer.parseInt(request.getParameter("id"));

            // Tạo một đối tượng OrderDetailDao
            OrderDetailDao orderDetailDao = new OrderDetailDao(DbCon.getConnection());
            OrderDao orderDao = new OrderDao(DbCon.getConnection());

            // Gọi phương thức getOrderDetailsByOrderId để lấy chi tiết đơn hàng
            List<OrderDetail> orderDetails = orderDetailDao.getOrderDetailsByOrderId(orderId);
            String address = orderDao.getAddressByOrderId(orderId);
            // Đặt danh sách chi tiết đơn hàng vào thuộc tính của request
            request.setAttribute("orderDetails", orderDetails);
            request.setAttribute("address", address);

            // Kiểm tra nếu người dùng đã xác thực là admin
            if (request.getSession().getAttribute("auth") != null && ((User) request.getSession().getAttribute("auth")).getRole().equals("admin")) {
                // Chuyển hướng sang trang JSP dành cho admin để hiển thị chi tiết đơn hàng
                RequestDispatcher dispatcher = request.getRequestDispatcher("detail-order-admin.jsp");
                dispatcher.forward(request, response);
            } else {
                // Chuyển hướng sang trang JSP dành cho người dùng thông thường để hiển thị chi tiết đơn hàng
                RequestDispatcher dispatcher = request.getRequestDispatcher("detail-order.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException|SQLException e) {
            // Xử lý ngoại lệ ở đây
            e.printStackTrace(); // hoặc log lỗi, hiển thị thông báo cho người dùng, ...
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
