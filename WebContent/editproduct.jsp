<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cn.techtutorial.model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Product product = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<%@include file="/includes/admin-navbar.jsp"%>
    <div class="container">
        <h3>Edit Product</h3>
        <form action="UpdateProductServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${product.id}" />
            <div class="form-group">
                <label for="name">Fruit Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${product.name}" required>
            </div>
            <div class="form-group">
                <label for="category">Category</label>
                <select class="form-control" id="category" name="category" required>
                    <option value="Fruit" ${product.category == 'Fruit' ? 'selected' : ''}>Fruit</option>
                    <option value="Berry" ${product.category == 'Berry' ? 'selected' : ''}>Berry</option>
                    <option value="Vegetable" ${product.category == 'Vegetable' ? 'selected' : ''}>Vegetable</option>
                    <option value="Exotic" ${product.category == 'Exotic' ? 'selected' : ''}>Exotic</option>
                    <option value="Citrus" ${product.category == 'Citrus' ? 'selected' : ''}>Citrus</option>
                    <option value="Miscellaneous" ${product.category == 'Miscellaneous' ? 'selected' : ''}>Miscellaneous</option>
                </select>
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" value="${product.price}" required>
            </div>
            <div class="form-group">
                <label for="image">Image</label>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="image" name="image" >
                    <label class="custom-file-label" for="image">Choose file</label>
                </div>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3">${product.description}</textarea>
            </div>
            <div class="form-group">
                <label for="season">Season</label>
                <select class="form-control" id="season" name="season" multiple>
                    <option value="Spring" ${product.season == 'Spring' ? 'selected' : ''}>Spring</option>
                    <option value="Summer" ${product.season == 'Summer' ? 'selected' : ''}>Summer</option>
                    <option value="Autumn" ${product.season == 'Autumn' ? 'selected' : ''}>Autumn</option>
                    <option value="Winter" ${product.season == 'Winter' ? 'selected' : ''}>Winter</option>
                </select>
            </div>
            <div class="form-group">
                <label for="origin">Origin</label>
                <select class="form-control" id="origin" name="origin">
                    <option value="United States" ${product.origin == 'United States' ? 'selected' : ''}>United States</option>
                    <option value="China" ${product.origin == 'China' ? 'selected' : ''}>China</option>
                    <option value="Mexico" ${product.origin == 'Mexico' ? 'selected' : ''}>Mexico</option>
                    <option value="Vietnam" ${product.origin == 'Vietnam' ? 'selected' : ''}>Vietnam</option>
                    <option value="India" ${product.origin == 'India' ? 'selected' : ''}>India</option>
                    <option value="Morocco" ${product.origin == 'Morocco' ? 'selected' : ''}>Morocco</option>
                    <option value="Thailand" ${product.origin == 'Thailand' ? 'selected' : ''}>Thailand</option>
                    <option value="Australia" ${product.origin == 'Australia' ? 'selected' : ''}>Australia</option>
                    <option value="Brazil" ${product.origin == 'Brazil' ? 'selected' : ''}>Brazil</option>
                    <option value="Italy" ${product.origin == 'Italy' ? 'selected' : ''}>Italy</option>
                    <option value="Chile" ${product.origin == 'Chile' ? 'selected' : ''}>Chile</option>
                    <option value="South Africa" ${product.origin == 'South Africa' ? 'selected' : ''}>South Africa</option>
                    <option value="Belgium" ${product.origin == 'Belgium' ? 'selected' : ''}>Belgium</option>
                    <option value="Costa Rica" ${product.origin == 'Costa Rica' ? 'selected' : ''}>Costa Rica</option>
                    <option value="Spain" ${product.origin == 'Spain' ? 'selected' : ''}>Spain</option>
                    <option value="Philippines" ${product.origin == 'Philippines' ? 'selected' : ''}>Philippines</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
