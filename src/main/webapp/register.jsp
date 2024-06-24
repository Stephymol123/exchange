
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<form action="user" method="post">
    <input type="hidden" name="action" value="register" />
    <label for="username">Username:</label></form>
    <input type="text" id="username" name="username" required /><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required /><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required /><br>
    <button type="submit">Register</button>

</form>
</body>
</html>