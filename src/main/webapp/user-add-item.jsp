<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
</head>
<body>
<form action="addProduct" enctype="multipart/form-data" method="post">
    <table align="center">
        <tr><td><h1>Add product details</h1></td></tr>
        <tr><td>Name:</td></tr>
        <tr><td><input type="text" name="pname" required/></td></tr>
        <tr><td>Price(Rs):</td></tr>
        <tr><td><input type="number" step="0.01" name="price" required/></td></tr>
        <tr><td>Description:</td></tr>
        <tr><td><textarea name="description" rows="4" cols="22" required></textarea></td></tr>
        <tr><td>Category:</td></tr>
        <tr><td>
            <select name="category_id" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.category}</option>
                </c:forEach>
            </select>
        </td></tr>
        <tr><td>Quantity:</td></tr>
        <tr><td><input type="number" name="quantity" required/></td></tr>
        <tr><td>Browse files<input type="file" name="pimages" accept="image/*" multiple required/></td></tr>
        <tr><td><input type="submit" value="Add"/></td>
            <td><a href="back1.html">Back</a></td></tr>
    </table>
</form>
</body>
</html>
