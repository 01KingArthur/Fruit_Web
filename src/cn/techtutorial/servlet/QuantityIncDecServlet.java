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
import cn.techtutorial.model.Cart;
import cn.techtutorial.model.User;

@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("id"));
        User auth = (User) request.getSession().getAttribute("auth");

    	System.out.print(action);
    	System.out.print(productId);
    	System.out.print(auth.getId());
        if (auth != null && action != null && productId >= 1) {
            try {

            	System.out.println("llll");
            	System.out.print("tang");
                CartDao cartDao = new CartDao(DbCon.getConnection());
                Cart cart = cartDao.getCartByUserAndProductId(auth.getId(), productId);
                if (action.equals("inc")) {
                    cartDao.updateQuantity(auth.getId(), productId, cart.getQuantity()+1);
                } else if (action.equals("dec") && cart.getQuantity()-1>=1) {
                    cartDao.updateQuantity(auth.getId(), productId, cart.getQuantity()-1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("cart.jsp");
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
//
//@WebServlet("/quantity-inc-dec")
//public class QuantityIncDecServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		try (PrintWriter out = response.getWriter()) {
//			String action = request.getParameter("action");
//			int id = Integer.parseInt(request.getParameter("id"));
//			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
//
//			if (action != null && id >= 1) {
//				if (action.equals("inc")) {
//					for (Cart c : cart_list) {
//						if (c.getId() == id) {
//							int quantity = c.getQuantity();
//							quantity++;
//							c.setQuantity(quantity);
//							response.sendRedirect("cart.jsp");
//						}
//					}
//				}
//
//				if (action.equals("dec")) {
//					for (Cart c : cart_list) {
//						if (c.getId() == id && c.getQuantity() > 1) {
//							int quantity = c.getQuantity();
//							quantity--;
//							c.setQuantity(quantity);
//							break;
//						}
//					}
//					response.sendRedirect("cart.jsp");
//				}
//			} else {
//				response.sendRedirect("cart.jsp");
//			}
//		}
//	}
//
//}
