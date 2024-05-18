package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.techtutorial.dao.CartDao;
import cn.techtutorial.model.Cart;
import cn.techtutorial.model.User;
import cn.techtutorial.connection.DbCon;

@WebServlet(name = "AddToCartServlet", urlPatterns = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            int productId = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            User auth = (User) session.getAttribute("auth");

            if (auth != null) {
                // Người dùng đã đăng nhập, lưu giỏ hàng vào cơ sở dữ liệu
                try {
                    CartDao cartDao = new CartDao(DbCon.getConnection());
                    Cart cart = cartDao.getCartByUserAndProductId(auth.getId(), productId);
                    
                    if (cart != null) {
                        // Nếu sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng lên 1
                        boolean updated = cartDao.updateQuantity(auth.getId(), productId, cart.getQuantity() + 1);
                        if (updated) {
                            response.sendRedirect("index.jsp");
                        } else {
                            out.println("<h3 style='color:crimson; text-align: center'>Failed to update cart. <a href='index.jsp'>Go to Home Page</a></h3>");
                        }
                    } else {
                        // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm mới vào
                        boolean added = cartDao.addToCart(auth.getId(), productId, 1);
                        if (added) {
                            response.sendRedirect("index.jsp");
                        } else {
                            out.println("<h3 style='color:crimson; text-align: center'>Failed to add to cart. <a href='index.jsp'>Go to Home Page</a></h3>");
                        }
                    }
                } catch (Exception e) {
                    out.println("<h3 style='color:crimson; text-align: center'>An error occurred while accessing the database. Please try again later.</h3>");
                    e.printStackTrace(); // In ra stack trace để debug
                }
            } else {
                // Người dùng chưa đăng nhập, yêu cầu đăng nhập
                out.println("<h3 style='color:crimson; text-align: center'>Please <a href='login.jsp'>login</a> to add items to your cart.</h3>");
            }
        }
    }
}


