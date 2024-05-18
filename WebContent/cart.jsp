<%@ page import="cn.techtutorial.connection.DbCon"%>
<%@ page import="cn.techtutorial.dao.CartDao"%>
<%@ page import="cn.techtutorial.dao.ProductDao"%>
<%@ page import="cn.techtutorial.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
User auth = (User) request.getSession().getAttribute("auth");
if (auth == null) {
    response.sendRedirect("login.jsp");
    return;
}

ArrayList<Cart> cartItems = new ArrayList<>();
if (auth != null) {
    CartDao cartDao = new CartDao(DbCon.getConnection());
    cartItems = (ArrayList<Cart>) cartDao.getCartByUserId(auth.getId());
}

List<Cart> cartProducts = null;
if (!cartItems.isEmpty()) {
    ProductDao productDao = new ProductDao(DbCon.getConnection());
    cartProducts = productDao.getCartProducts(auth.getId());
    double total = productDao.getTotalCartPrice(cartItems);
   	System.out.print(total);
    request.setAttribute("total", total);
    request.setAttribute("cartItems", cartItems);
}
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/includes/head.jsp"%>
    <title>Your Cart</title>
    <style type="text/css">
        .table tbody td {
            vertical-align: middle;
        }
        .btn-incre, .btn-decre {
            box-shadow: none;
            font-size: 25px;
        }
    </style>
    <script>
        function requestAddress() {
            var address = prompt("Enter your address( Be carefully! Because your order can be canceled if you enter the wrong address)");
            if (address != null && address.trim() !== "") {
                placeOrder(address);
            } else {
                alert("Please provide your address.");
                requestAddress();
            }
        }

        function placeOrder(address) {
            var confirmOrder = confirm("Are you sure you want to place the order?");
            if (confirmOrder) {
            	var total = document.getElementById("total").innerText;
                document.getElementById("address").value = address;
                document.getElementById("totalHidden").value = total;
                document.getElementById("checkoutForm").submit();
            }
        }
    </script>
</head>
<body>
	
    <%@include file="/includes/navbar-login.jsp"%>
	<form id="checkoutForm" action="place-order" method="post">
	    <input type="hidden" id="address" name="address" value="">
	    <input type="hidden" id="totalHidden" name="total" value="">
	</form>
	
    <div class="container my-3">
        <div class="d-flex py-3">
            <h3>Total Price: $<span id="total"><%= (request.getAttribute("total") != null && (double)request.getAttribute("total") > 0) ? dcf.format((double)request.getAttribute("total")) : "0" %></span> </h3>
            <a class="mx-3 btn btn-primary" onclick="requestAddress()">Check Out</a>
        </div>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Remove</th>
                </tr>
            </thead>
            <tbody>
				<%
				if (cartProducts != null) {
					for (Cart c : cartProducts) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%= dcf.format(c.getPrice())%></td>
					<td>
						<form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getProductId()%>"><i class="fas fa-plus-square"></i></a> 
								<input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly> 
								<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getProductId()%>"><i class="fas fa-minus-square"></i></a>
							</div>
							<!-- <button type="submit" class="btn btn-primary btn-sm">Buy</button> -->
						</form>
					</td>
					<td><a href="remove-from-cart?id=<%=c.getProductId() %>" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>

				<%
				}}else {
					%>
			        <tr>
			            <td colspan="5">Cart have no product yet</td>
			        </tr>
				<%
				}
				%>
				
			</tbody>
        </table>
    </div>

    <%@include file="/includes/footer.jsp"%>
</body>
</html>


<%-- 
<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pDao = new ProductDao(DbCon.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	double total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("total", total);
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>Fruitopia</title>
<style type="text/css">

.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 25px;
}
</style>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container my-3">
		<div class="d-flex py-3"><h3>Total Price: $ ${(total>0)?dcf.format(total):0} </h3> <a class="mx-3 btn btn-primary" href="orders.jsp">Check Out</a></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%= dcf.format(c.getPrice())%></td>
					<td>
						<form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a> 
								<input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly> 
								<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a href="remove-from-cart?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>

				<%
				}}%>
			</tbody>
		</table>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>--%>