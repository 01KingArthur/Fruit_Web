<%@page import="java.util.List"%>
<%@page import="cn.techtutorial.model.User"%>
<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
UserDao userData = new UserDao(DbCon.getConnection());
List<User> users = userData.getAllUser();
request.setAttribute("USERS_LIST", users);
%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<title>CRUD Application</title>

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
    width: 700px; /* Điều chỉnh độ rộng của thanh tìm kiếm */
}
</style>
</head>
<body>
    <%@include file="/includes/admin-navbar.jsp"%>
    <div class="container-fluid">
        <nav class="navbar navbar-light">
            <form class="form-inline custom-search-form" action="SearchUserServlet" method="get">
                <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search Users..." aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </nav>
    </div>
    <div class="container">
        <div class="inner">
            <div class="row">
                <div class="col-md-9">
                    <h3>User Information</h3>
                    <table class="table">
                        <thead class="bg-light">
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${not empty SEARCH_RESULTS}">
                                <c:forEach var="user" items="${SEARCH_RESULTS}">
                                    <tr>
                                    <td>${user.name }</td>
                                    <td>${user.email }</td>
                                    <td>
                                        <a href="DeleteUserServlet?id=${user.id}">Delete</a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty SEARCH_RESULTS}">
                                <c:forEach var="user" items="${USERS_LIST}">
                                    <tr>
                                    <td>${user.name }</td>
                                    <td>${user.email }</td>
                                    <td>
                                        <a href="DeleteUserServlet?id=${user.id}">Delete</a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
