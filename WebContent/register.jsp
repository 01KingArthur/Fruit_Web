<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
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
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">User Registration</div>
            <div class="card-body">
                <!-- Registration Form -->
                <form action="user-registration" method="post">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" name="registration-name" class="form-control" placeholder="Enter your name">
                    </div>
                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" name="registration-email" class="form-control" placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="registration-password" class="form-control" placeholder="Password">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Register</button>
                    </div>
                </form>

                <!-- Login Button -->
                <div class="text-center mt-3">
				    <button type="button" onclick="location.href='login.jsp'" class="btn btn-link text-primary">Login</button>
				</div>

            </div>
        </div>
    </div>

    <%@include file="/includes/footer.jsp"%>
</body>
</html>
