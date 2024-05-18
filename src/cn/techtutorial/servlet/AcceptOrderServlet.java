package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.OrderDao;




@WebServlet("/accept-order")
public class AcceptOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
	        String id = request.getParameter("id");
	        if(id != null) {
	            int orderId = Integer.parseInt(id);
	            OrderDao orderDao = new OrderDao(DbCon.getConnection());
	            
	            // Lấy trạng thái hiện tại của đơn hàng
	            String currentStatus = orderDao.getOrderStatus(orderId);
	            
	            // Kiểm tra xem trạng thái có là "DELIVERED" không
	            if (!"PENDING".equals(currentStatus)) {
	                // Nếu là "DELIVERED", gửi thông báo lỗi và không thực hiện hành động hủy
	                response.sendRedirect("error.jsp");
	                return;
	            }
	            
	            // Nếu không phải là "DELIVERED", tiến hành cập nhật trạng thái đơn hàng thành "CANCELLED"
	            orderDao.updateOrderStatus(orderId, "DELIVERED");
	        }
	        response.sendRedirect("admin-orders.jsp");
	    } catch (ClassNotFoundException|SQLException e) {
	        // Xử lý ngoại lệ
	        e.printStackTrace();
	    } 
    }
}
