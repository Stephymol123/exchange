<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
</head>
<body>
<form action="AddProductController" enctype="multipart/form-data" method="post">
    <table align="center">
        <tr><td><h1>Add product details</h1></td></tr>
        <tr><td>Name:</td></tr>
        <tr><td><input type="text" name="pname"/></td></tr> <!-- Adjusted to match ProductBeanCls -->
        <tr><td>Price(Rs):</td></tr>
        <tr><td><input type="text" name="price"/></td></tr>
        <tr><td>Description:</td></tr>
        <tr><td><textarea name="description" rows="4" cols="22"></textarea></td></tr>
        <tr><td>Category:</td></tr>
        <tr><td>
            <select name="category_id">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.category}</option>
                </c:forEach>
            </select>
        </td></tr>
        <tr><td>Quantity:</td></tr>
        <tr><td><input type="text" name="quantity"/></td></tr>
        <tr><td>Browse file<input type="file" name="pimage"/></td></tr>
        <tr><td><input type="submit" value="Add"/></td>
            <td><a href="back1.html">Back</a></td></tr>
    </table>
</form>
</body>
</html>
