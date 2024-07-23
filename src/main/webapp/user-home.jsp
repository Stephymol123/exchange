<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Welcome, <c:out value="${user.username}" /></h2>
<c:if test="${not empty message}">
    <p style="color:red;">${message}</p>
</c:if>

<a href="addProduct">add new  item</a>
<a href="allProducts">View all Items</a>
</body>
</html>
