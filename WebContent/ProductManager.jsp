<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="cn.techtutorial.model.Product"%>
<%@page import="cn.techtutorial.connection.DbCon"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ProductDao productData = new ProductDao(DbCon.getConnection());
List<Product> products = productData.getAllProducts();
request.setAttribute("PRODUCTS_LIST", products);
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
            <!--<form class="form-inline">
                <input class="form-control mr-sm-2" type="search"
                    placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>-->
            <form class="form-inline custom-search-form" action="SearchProductServlet" method="get">
                <input class="form-control mr-sm-2 centered-search" type="search" name="search" placeholder="Search Products..." aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
            
        </nav>
    </div>
    <div class="container">
        <div class="inner">
            <div class="row">
                <div class="col-md-3">
                    <h3>Add Fruit</h3>
                    <form action="AddProductServlet" method="post" enctype="multipart/form-data">
					    <div class="form-group">
					        <label for="name">Fruit Name</label>
					        <input type="text" class="form-control" id="name" name="name" placeholder="Fruit Name" required>
					    </div>
					    <div class="form-group">
						    <label for="category">Category</label>
						    <select class="form-control" id="category" name="category" required>
						        <option value="">Select a category</option>
						        <option value="Fruit">Fruit</option>
						        <option value="Berry">Berry</option>
						        <option value="Vegetable">Vegetable</option>
						        <option value="Exotic">Exotic</option>
						        <option value="Citrus">Citrus</option>
						        <option value="Miscellaneous">Miscellaneous</option>
						    </select>
						</div>

					    <div class="form-group">
							    <label for="price">Price</label>
							    <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price ex: 1.4" required>
							</div>

					    <div class="form-group">
						    <label for="image">Image</label>
						    <div class="custom-file">
						        <input type="file" class="custom-file-input" id="image" name="image" required>
						        <label class="custom-file-label" for="image">Choose file</label>
						    </div>
						</div>

					    <div class="form-group">
					        <label for="description">Description</label>
					        <textarea class="form-control" id="description" name="description" rows="3" placeholder="Description"></textarea>
					    </div>
					    <div class="form-group">
					        <label for="season">Season</label>
					        <select class="form-control" id="season" name="season" multiple>
						        <option value="Spring">Spring</option>
						        <option value="Summer" selected>Summer</option>
						        <option value="Autumn">Autumn</option>
						        <option value="Winter">Winter</option>
						    </select>
					    </div>
					    <div class="form-group">
						    <label for="origin">Origin</label>
						    <select class="form-control" id="origin" name="origin">
						        <option value="">Select origin</option>
						        <option value="United States">United States</option>
						        <option value="China">China</option>
						        <option value="Mexico">Mexico</option>
						        <option value="Vietnam">Vietnam</option>
						        <option value="India">India</option>
						        <option value="Morocco">Morocco</option>
						        <option value="Thailand">Thailand</option>
						        <option value="Australia">Australia</option>
						        <option value="Brazil">Brazil</option>
						        <option value="Italy">Italy</option>
						        <option value="Chile">Chile</option>
						        <option value="South Africa">South Africa</option>
						        <option value="Belgium">Belgium</option>
						        <option value="Costa Rica">Costa Rica</option>
						        <option value="Spain">Spain</option>
						        <option value="Philippines">Philippines</option>
						    </select>
						</div>

					    <button type="submit" class="btn btn-primary">Submit</button>
					    <button type="reset" class="btn btn-primary">Reset</button>
					</form>

                </div>
                <div class="col-md-9">
                    <h3>Fruit Information</h3>
                    <table class="table">
                        <thead class="bg-light">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Category</th>
                                <th scope="col">Price</th>
                                <th scope="col">Image</th>
                                <th scope="col">Description</th>
                                <th scope="col">Season</th>
                                <th scope="col">Origin</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${not empty SEARCH_RESULTS}">
                                <c:forEach var="product" items="${SEARCH_RESULTS}">
                                    <tr>
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td>${product.category}</td>
                                    <td>${product.price}</td>
                                    <td>${product.image}</td>
                                    <td>${product.description}</td>
                                    <td>${product.season}</td>
                                    <td>${product.origin}</td>
                                    <td>
                                        <a href="editproduct.jsp?id=${product.id}">Edit</a> 
                                        <a href="DeleteProductServlet?id=${product.id}">Delete</a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty SEARCH_RESULTS}">
                                <c:forEach var="product" items="${PRODUCTS_LIST}">
                                    <tr>
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td>${product.category}</td>
                                    <td>${product.price}</td>
                                    <td>${product.image}</td>
                                    <td>${product.description}</td>
                                    <td>${product.season}</td>
                                    <td>${product.origin}</td>
                                    <td>
                                        <a href="EditProductServlet?id=${product.id}">Edit</a> 
                                        <a href="DeleteProductServlet?id=${product.id}">Delete</a>
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
