
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="cn.techtutorial.model.*"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
    <h1>Error</h1>
    <p>Sorry, the order cannot be implemented because it has already been delivered or canceled.</p>
    <%
        User auth = (User) request.getSession().getAttribute("auth");
        String redirectPage = (auth != null && "admin".equals(auth.getRole())) ? "admin-orders.jsp" : "orders.jsp";
    %>
    <form action="<%= redirectPage %>">
        <input type="submit" value="OK">
    </form>
</body>
</html>
