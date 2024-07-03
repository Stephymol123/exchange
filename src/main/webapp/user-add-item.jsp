<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 02-07-2024
  Time: 11:57 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form action="productsadd.html" enctype="multipart/form-data" method="post">
    <table align="center">
        ${suc}
        <tr><td><h1>Add product details</h1></td></tr>

        <tr><td>Name:</td></tr>
        <tr><td><input type="text" name="name"/></td></tr>
        <tr><td>Price(Rs):</td></tr>
        <tr><td><input type="text" name="price"/></td></tr>
        <tr><td>Description:</td>
        <tr><td><textarea name="description" rows="4" cols="22"></textarea></td></tr>
        <tr><td>Category:</td><td>
        <tr><td><input list="category" name="category">
            <datalist id="category">
                <option value="Electronics">
                <option value="Clothing">
                <option value="Services">
            </datalist></td></tr>
        <tr><td>Width(in cm):</td></tr>
        <tr><td><input type="text" name="width"/></td></tr>
        <tr><td>Height(in cm):</td></tr>
        <tr><td><input type="text" name="height"/></td></tr>
        <tr><td>Weight(in gm):</td></tr>
        <tr><td><input type="text" name="weight"/></td></tr>
        <tr><td>Quantity:</td></tr>
        <tr><td><input type="text" name="quantity"/></td></tr>
        <tr><td>Shipping fee(Rs):</td></tr>
        <tr><td><input type="text" name="shippingfee"/></td></tr>
        <tr><td>Browse file<input type="file" name="pimage"/></td>
        <tr><td><input type="submit" value="Add"/></td>
            <td><a href="back1.html">Back</a></td></tr>
    </table>
</form>
</body>
</html>
