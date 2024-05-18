<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.dao.CategoryDao"%>
<%@page import="cn.techtutorial.dao.OriginDao"%>
<%@page import="cn.techtutorial.dao.SeasonDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%    
    
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
}
ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}

CategoryDao categoryDao = new CategoryDao(DbCon.getConnection());
List<Category> categories = categoryDao.getAllCategories();
request.setAttribute("categories", categories);

OriginDao OriginDao = new OriginDao(DbCon.getConnection());
List<Origin> origins = OriginDao.getAllOrigins();
request.setAttribute("origins", origins);

SeasonDao SeasonDao = new SeasonDao(DbCon.getConnection());
List<Season> seasons = SeasonDao.getAllSeasons();
request.setAttribute("seasons", seasons);


%>

<%
String selectedCategory = request.getParameter("category");

String selectedOrigin = request.getParameter("origin");

String selectedSeason = request.getParameter("season");
if (selectedCategory == null || selectedCategory.isEmpty()) {
    selectedCategory = "All";
}
if (selectedOrigin == null || selectedOrigin.isEmpty()) {
    selectedOrigin = "All";
}
if (selectedSeason == null || selectedSeason.isEmpty()) {
    selectedSeason = "All";
}
 products = pd.getProductsByFilters(selectedCategory, selectedOrigin, selectedSeason);
/*if (selectedCategory != null && !selectedCategory.isEmpty() && selectedCategory != "All Products") {
    products = pd.getProductsByCategory(selectedCategory);
    request.setAttribute("selectedCategory", selectedCategory);
} else {
    products = pd.getAllProducts();
    request.setAttribute("selectedCategory", "All Products");
    selectedCategory = "All Products";
}*/
%>
<%
String searchTerm = request.getParameter("search");
if (searchTerm != null && !searchTerm.isEmpty()) {
    products = pd.searchProducts(searchTerm);
    
}
%>

<script>
    function showProductsByCategory(category) {
        window.location.href = 'index.jsp?category=' + encodeURIComponent(category);
    }
</script>

<!DOCTYPE html>
<html>
<head>
<style>

	.card-header {
	  display: flex;
	  justify-content: space-between; 
	
	}
	header {
	  justify-content: space-between;
	  
	  position: sticky;
	  top: 0;
	  z-index: 9999;
		html {
		  font-size: 62.5%;
		  scroll-behavior: smooth;
		}
	}
	body{
		background: #99FF99;
	}
	.side-left img {
	    width: 350px;
	    height: 600;
	    border-radius: 10px; /* Optional: to add rounded corners */
	    justify-content: space-between;
	  
		position: sticky;
		top: 250;
		z-index: 9999;
		html {
		  font-size: 62.5%;
		  scroll-behavior: smooth;
		}
	}
	.side-right img {
	    width: 350px;
	    height: 600;
	    border-radius: 10px; /* Optional: to add rounded corners */
	    justify-content: space-between;
	  
		position: sticky;
		top: 250;
		z-index: 9999;
		html {
		  font-size: 62.5%;
		  scroll-behavior: smooth;
		}
	}
	.main-container {
		background: #EEEEEE;
	    display: flex;
	    justify-content: center;
	}
</style>
<%@include file="/includes/head.jsp"%>
<title>Fruitopia</title>

</head>
<body>
	<header>
		<%@include file="/includes/navbar.jsp"%>
	</header>
   

	
<div class="main-container">
	<div class="side-left">
        <img src="design-image/sideleft.jpg" alt="Side Image 1">
    </div>
	<div class="container">
            <div class="card-header my-3"  style="background-color:#DDDDDD;">
                <label for="categorySelect">Select Category: </label>
                <select id="categorySelect" >
	                <option value="All">All Products</option>
	                <% 
	                	for (Category category : categories) {
	                %>
	                <option value="<%= category.getCategoryName() %>" 
	                    <% if (category.getCategoryName().equals(selectedCategory)) { %>
	                        selected
	                    <% } %>
	                ><%= category.getCategoryName() %></option>
	                <%
	                }
	                %>
	            	</select>
            	<label for="originSelect">Select Origin: </label>
			    <select id="originSelect" >
			        <option value="All">All Origins</option>
			        <% 
				    for (Origin origin : origins) {
				    %>
				    <option value="<%= origin.getOriginName()  %>" 
				        <% if (origin.getOriginName().equals(selectedOrigin)) { %>
				            selected
				        <% } %>
				    ><%= origin.getOriginName() %></option>
				    <%
				    }
				    %>
    			</select>
    			<label for="seasonSelect">Select Season: </label>
				    <select id="seasonSelect">
			        <option value="All">All Seasons</option>
			        <% 
				    for (Season season : seasons) {
				    %>
			    	<option value="<%= season.getSeasonName()  %>" 
				        <% if (season.getSeasonName().equals(selectedSeason)) { %>
				            selected
				        <% } %>
				    ><%= season.getSeasonName() %></option>
				    <%
				    }
				    %>
				    </select>
    			<label for="likedProduct">Liked Product: </label>
			    <select id="likedProduct" >
			        <option value="-1">None</option>
			        <option value="Increase">Increase</option>
			        <option value="Decrease">Decrease</option>
			    </select>
			    <div  onclick="showProductsByFilters()">
				  <img width = 40 height = 40 src="design-image/filter.png" alt="Your Image">
				</div>
            </div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top " width = 300 height = 200 src="product-image/<%=p.getImage() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price: $<%=p.getPrice() %></h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add to Cart</a> <a
								class="btn btn-primary" href="description?id=<%=p.getId()%>">Description</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no products");
			}
			%>

		</div>
	</div>
	<div class="side-right">
            <img src="design-image/sideright.jpg" alt="Side Image 1">
        </div>
</div>
                        
        <script>
        function  showProductsByFilters(){
            const category = document.getElementById('categorySelect').value;
            const origin = document.getElementById('originSelect').value;
            const season = document.getElementById('seasonSelect').value;
            window.location.href = 'index.jsp?category=' + encodeURIComponent(category) + '&origin=' + encodeURIComponent(origin) + '&season=' + encodeURIComponent(season);
        }
        function showProductsByCategory(category) {
            if (category === "-1") {
                window.location.href = 'index.jsp';
            } else {
                window.location.href = 'index.jsp?category=' + encodeURIComponent(category);
            }
        }
        </script>
	<%@include file="/includes/home-footer.jsp"%>
</body>
</html>