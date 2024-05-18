package cn.techtutorial.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.CartDao;
import cn.techtutorial.model.User;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Lấy thông tin sản phẩm cần xóa từ request
        int productId = Integer.parseInt(request.getParameter("id"));
        
        // Lấy thông tin người dùng hiện tại từ session
        User auth = (User) request.getSession().getAttribute("auth");

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (auth != null && productId >= 1) {
            try {
                // Tạo đối tượng CartDao để thao tác với cơ sở dữ liệu
                CartDao cartDao = new CartDao(DbCon.getConnection());
                
                // Xóa sản phẩm khỏi giỏ hàng của người dùng hiện tại
                boolean removed = cartDao.removeFromCart(auth.getId(), productId);
                
                if (removed) {
                    // Nếu xóa thành công, chuyển hướng người dùng đến trang giỏ hàng
                    response.sendRedirect("cart.jsp");
                } else {
                    // Nếu xóa thất bại, hiển thị thông báo lỗi
                    response.getWriter().println("<h3 style='color:crimson; text-align: center'>Failed to remove item from cart. Please try again later.</h3>");
                }
            } catch (Exception e) {
                // Nếu có lỗi xảy ra trong quá trình xóa, hiển thị thông báo lỗi và ghi log
                response.getWriter().println("<h3 style='color:crimson; text-align: center'>An error occurred while removing item from cart. Please try again later.</h3>");
                e.printStackTrace(); // Ghi log
            }
        } else {
            // Nếu người dùng chưa đăng nhập hoặc productId không hợp lệ, chuyển hướng người dùng đến trang giỏ hàng
            response.sendRedirect("cart.jsp");
        }
    }
}

//package cn.techtutorial.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import cn.techtutorial.model.Cart;
//import cn.techtutorial.connection.DbCon;
//import cn.techtutorial.dao.CartDao;
//import cn.techtutorial.model.*;
//@WebServlet("/remove-from-cart")
//public class RemoveFromCartServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		try (PrintWriter out = response.getWriter()) {
//			String bookId = request.getParameter("id");
//			User auth = (User) request.getSession().getAttribute("auth");
//			int productId = Integer.parseInt(request.getParameter("id"));
//			if (productId > 0) {
//				CartDao cartDao = new CartDao(DbCon.getConnection());
//                Cart cart = cartDao.getCartByUserAndProductId(auth.getId(), productId);
//				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
//				if (cart_list != null) {
//					for (Cart c : cart_list) {
//						if (c.getId() == Integer.parseInt(bookId)) {
//							cart_list.remove(cart_list.indexOf(c));
//							break;
//						}
//					}
//				}
//				response.sendRedirect("cart.jsp");
//
//			} else {
//				response.sendRedirect("cart.jsp");
//			}
//
//		}
//	}
//
//}
