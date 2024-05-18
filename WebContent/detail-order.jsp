<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.OrderDetailDao"%>
<%@page import="cn.techtutorial.dao.OrderDao"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
DecimalFormat dcf = new DecimalFormat("#.##");

// Kiểm tra xem người dùng đã đăng nhập hay chưa
User auth = (User) request.getSession().getAttribute("auth");
if (auth == null) {
    response.sendRedirect("login.jsp");
    return;
}
List<OrderDetail> orderDetails = (List<OrderDetail>) request.getAttribute("orderDetails");
System.out.println("----");
System.out.println(orderDetails.get(0).getPrice());
String address = (String) request.getAttribute("address");
// Lấy thông tin đơn hàng từ cơ sở dữ liệu
OrderDetailDao orderDetailDao = new OrderDetailDao(DbCon.getConnection());
OrderDao orderDao = new OrderDao(DbCon.getConnection());
// Tính tổng số tiền
double total = 0;
for (OrderDetail orderDetail : orderDetails) {
    total += orderDetail.getPrice() * orderDetail.getQuantity();
}
String formattedTotal = dcf.format(total);
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/includes/head.jsp"%>
    <title>Order Detail</title>
</head>
<body>
    <%@include file="/includes/navbar-login.jsp"%>

    <div class="container my-3">
        <h2>Order Detail</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Category</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="orderDetail" items="${orderDetails}">
                        <tr>
                            <td>${orderDetail.getProductId()}</td>
                            <td>${orderDetail.getProductName()}</td>
                            <td>${orderDetail.getCategory()}</td>
                            <td>${orderDetail.getQuantity()}</td>
                            <td>$${orderDetail.getPrice()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="row">
            <div class="col">
                <h4>Address: <%= address %></h4>
            </div>
            <div class="col">
                <h4>Total: $<%= formattedTotal %></h4>
            </div>
        </div>
    </div>

    <%@include file="/includes/footer.jsp"%>
</body>
</html>
