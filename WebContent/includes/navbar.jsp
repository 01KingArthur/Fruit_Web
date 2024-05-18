<%@ page import="cn.techtutorial.connection.DbCon"%>
<%@ page import="cn.techtutorial.dao.CartDao"%>
<%@ page import="cn.techtutorial.dao.ProductDao"%>
<%@ page import="cn.techtutorial.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
DecimalFormat dcf = new DecimalFormat("#");
User auth1 = (User) request.getSession().getAttribute("auth");

ArrayList<Cart> cartItems = new ArrayList<>();
if (auth1 != null) {
    CartDao cartDao = new CartDao(DbCon.getConnection());
    cartItems = (ArrayList<Cart>) cartDao.getCartByUserId(auth1.getId());
}
String cartsize = dcf.format(cartItems.size());
%>

<style>
.nav-item {
  font-size: 30px;
  font-weight: bold;
}
.search-container {
    display: flex;
    justify-content: center;
    background-color: #3366FF; 
    padding: 50px 20px;
}

.search-container input[type=text] {
    padding: 8px;
    font-size: 16px;
    border: none;
    border-radius: 5px 0 0 5px;
    background-color: white;
    width: 50%;
}

.search-container button {
    padding: 8px 15px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    border-radius: 0 5px 5px 0;
    background-color: #4CAF50; 
    color: white;
}

.search-container button:hover {
    background-color: #45a049;
}
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<img src="product-image/logo_fs.png"  width="80" height="80">
		<a class="navbar-brand" style="font-size:30;" href="index.jsp">Fruitopia</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="index.jsp">Blog</a></li>
				<%
				if (auth != null) {
				%>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart <span class="badge badge-danger"><%= cartsize %></span> </a></li>
				<li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>

<div class="search-container">
        <input type="text" placeholder="Search for products e.g. bananas, apple" id="search" name="search">
        <button type="button" onclick="searchProducts()">Find</button>
    </div>
<script>
function searchProducts() {
    const searchTerm = document.getElementById('search').value.trim();
    if (searchTerm !== '') {
        window.location.href = 'index.jsp?search=' + encodeURIComponent(searchTerm);
    }
}
</script>

   