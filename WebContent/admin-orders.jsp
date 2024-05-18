<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="cn.techtutorial.dao.OrderDao"%>
<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
OrderDao orderDao = new OrderDao(DbCon.getConnection());
List<Order> order = orderDao.getAllOrders();
request.setAttribute("ORDERS_LIST", order);
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Fruitopia</title>
<style>
.inner {
    margin: 15px 0;
}
.custom-search-form {
    display: flex;
    justify-content: right;
    width: 100%;
}

.custom-search-form .form-control {
    width: 700px; 
}
</style>
</head>
<body>
    <%@include file="/includes/admin-navbar.jsp"%>
    <div class="container-fluid">
        <nav class="navbar navbar-light">
            <!--<form class="form-inline">
                <input class="form-control mr-sm-2" type="search"
                    placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>-->
            <form class="form-inline custom-search-form" action="SearchOrderServlet" method="get">
                <input class="form-control mr-sm-2 centered-search" type="search" name="search" placeholder="Search Products..." aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
            
        </nav>
    </div>
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
                <th scope="col">Accept</th>
                <th scope="col">Reject</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty SEARCH_RESULTS}">
             <c:forEach var="order" items="${SEARCH_RESULTS}">
                 <tr>
                    <td>${order.orderId}</td>
                    <td>${order.userId}</td>
                    <td>${order.address}</td>
                    <td>${order.status}</td>
                    <td>${order.o_date}</td>
                    <td>${order.o_quantity}</td>
                    <td>${order.total_price}</td>
                    <td><a href="accept-order?id=${order.orderId}" class="btn btn-sm btn-success">Accept</a></td>
                    <td><a href="cancel-order?id=${order.orderId}" class="btn btn-sm btn-danger">Reject</a></td>
                </tr>
             </c:forEach>
         </c:if>
         <c:if test="${empty SEARCH_RESULTS}">
             <c:forEach var="order" items="${ORDERS_LIST}">
                 <tr>
                    <td>${order.orderId}</td>
                    <td>${order.userId}</td>
                    <td>${order.address}</td>
                    <td>${order.status}</td>
                    <td>${order.o_date}</td>
                    <td>${order.o_quantity}</td>
                    <td>${order.total_price}</td>
                    <td><a href="accept-order?id=${order.orderId}" class="btn btn-sm btn-success">Accept</a></td>
                    <td><a href="cancel-order?id=${order.orderId}" class="btn btn-sm btn-danger">Reject</a></td>
                    <td><a class="btn btn-sm btn-primary" href="order-details?id=${order.orderId}">Detail</a></td>
                </tr>
             </c:forEach>
         </c:if>
        </tbody>
    </table>
</div>

    <%@include file="/includes/footer.jsp"%>
</body>
</html>
