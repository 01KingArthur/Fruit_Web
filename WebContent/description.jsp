<%@page import="cn.techtutorial.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
    Product product = (Product) request.getAttribute("product");
    if (product == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Description - <%= product.getName() %></title>
    <style>
        body {
            background: #f0f0f0;
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .product-image {
            width: 100%;
            max-width: 300px;
            margin: auto;
            display: block;
        }
        .product-details {
            margin-top: 20px;
        }
        .product-description {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Description - <%= product.getName() %></h1>
        <img class="product-image" src="product-image/<%= product.getImage() %>" alt="<%= product.getName() %>">
        <div class="product-details">
            <p><strong>Category:</strong> <%= product.getCategory() %></p>
            <p><strong>Price:</strong> $<%= product.getPrice() %></p>
            <p><strong>Origin:</strong> <%= product.getOrigin() %></p>
            <p><strong>Season:</strong> <%= product.getSeason() %></p>
        </div>
        <div class="product-description">
            <p><%= product.getDescription() %></p>
        </div>
        <a href="index.jsp">Back to Products</a>
    </div>
</body>
</html>
