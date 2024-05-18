package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.CartDao;
import cn.techtutorial.dao.OrderDao;
import cn.techtutorial.model.Cart;
import cn.techtutorial.model.Order;
import cn.techtutorial.model.User;
import cn.techtutorial.dao.OrderDetailDao;
import cn.techtutorial.model.OrderDetail;

@WebServlet("/place-order")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User auth = (User) request.getSession().getAttribute("auth");
            String address = request.getParameter("address");
            String totalStr = request.getParameter("total");
            totalStr = totalStr.replace(",", ".");
            Double total = Double.parseDouble(totalStr);

            if (auth != null && address != null && !address.isEmpty()) {
            	CartDao cartDao = new CartDao(DbCon.getConnection());
                ArrayList<Cart> cart = (ArrayList<Cart>)cartDao.getCartByUserId(auth.getId());
                
                if (cart != null) {
                	int totalQuantity = cartDao.getTotalQuantity(auth.getId());
                    if (totalQuantity <= 0) {
                        response.sendRedirect("cart.jsp");
                        return;
                    }
                    OrderDao orderDao = new OrderDao(DbCon.getConnection());
                    Order order = new Order();
                    order.setUserId(auth.getId());
                    order.setAddress(address);
                    order.setStatus("PENDING");
                    order.setO_date(formatter.format(date));
                    order.setO_quantity(totalQuantity);
                    order.setTotal_price(total);

                    boolean result = orderDao.insertOrder(order);
                    if (!result) {
                        out.println("Order failed");
                        return;
                    }
                    int orderId = orderDao.getLastOrderId(); // Lấy orderId của order vừa thêm vào
                    
                    OrderDetailDao orderdetailDao = new OrderDetailDao(DbCon.getConnection());
                    // Thêm các sản phẩm vào bảng orderdetail
                    for (Cart item : cart) {
                        result = orderdetailDao.addOrderDetail(orderId, item.getProductId(), item.getQuantity());
                        if (!result) {
                            out.println("Adding order detail failed");
                            return;
                        }
                    }
                    cartDao.removeAll(auth.getId());
                    response.sendRedirect("cart.jsp");
                } else {
                    out.println("Cart is empty");
                }
            } else {
                out.println("Invalid authentication or address");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}


//package cn.techtutorial.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import cn.techtutorial.connection.DbCon;
//import cn.techtutorial.dao.OrderDao;
//import cn.techtutorial.model.*;
//
//
//@WebServlet("/cart-check-out")
//public class CheckOutServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//   
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try(PrintWriter out = response.getWriter()){
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = new Date();
//			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
//			User auth = (User) request.getSession().getAttribute("auth");
//			if(auth!=null) {
//				if(cart_list != null) {
//					for(Cart c:cart_list) {
//						Order order = new Order();
//						order.setId(c.getId());
//						order.setUid(auth.getId());
//						order.setQunatity(c.getQuantity());
//						order.setDate(formatter.format(date));
//						
//						OrderDao oDao = new OrderDao(DbCon.getConnection());
//						boolean result = oDao.insertOrder(order);
//						if(!result) break;
//					}
//				}
//				cart_list.clear();
//				response.sendRedirect("orders.jsp");
//			}else {
//				response.sendRedirect("login.jsp");
//			}
//		} catch (ClassNotFoundException|SQLException e) {
//			
//			e.printStackTrace();
//		}
//	}
//
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
