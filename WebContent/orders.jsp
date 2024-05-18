<%@page import="java.text.DecimalFormat"%>
<%@page import="cn.techtutorial.dao.OrderDao"%>
<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
List<Order> orders = null;
if (auth != null) {
    request.setAttribute("person", auth);
    OrderDao orderDao  = new OrderDao(DbCon.getConnection());
	orders = orderDao.getUserOrders(auth.getId());
}else{
	response.sendRedirect("login.jsp");
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Fruitopia</title>
</head>
<body>
	<%@include file="/includes/navbar-login.jsp"%>
	<div class="container">
    <div class="card-header my-3">All Orders</div>
    <table class="table table-light">
        <thead>
            <tr>
                <th scope="col">Order ID</th>
                <th scope="col">User ID</th>
                <th scope="col">Address</th>
                <th scope="col">Status</th>
                <th scope="col">Order Date</th>
                <th scope="col">Quantity</th>
                <th scope="col">Total Price</th>
                <th scope="col">Cancel</th>
            </tr>
        </thead>
        <tbody>
            <% if (orders != null) { 
                for (Order order : orders) { %>
                    <tr>
                        <td><%= order.getOrderId() %></td>
                        <td><%= order.getUserId() %></td>
                        <td><%= order.getAddress() %></td>
                        <td><%= order.getStatus() %></td>
                        <td><%= order.getO_date() %></td>
                        <td><%= order.getO_quantity() %></td>
                        <td>$<%= dcf.format(order.getTotal_price()) %></td>
                        <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%= order.getOrderId()%>">Cancel Order</a></td>
                        <td><a class="btn btn-sm btn-primary" href="order-details?id=<%= order.getOrderId()%>">Detail</a></td>
                    </tr>
                <% }
            } %>
        </tbody>
    </table>
</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>