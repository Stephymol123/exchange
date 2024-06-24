<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f2f5;
        }
        .navbar {
            background-color: rgba(0, 0, 0, 0.7);
            overflow: hidden;
            padding: 15px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .navbar a {
            color: white;
            padding: 14px 20px;
            text-decoration: none;
            text-align: center;
        }
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
        .login-container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin: auto;
        }
        .login-container h2 {
            margin-bottom: 20px;
            text-align: center;
        }
        .login-container label {
            display: block;
            margin-bottom: 5px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .login-container input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .footer {
            background-color: rgba(0, 0, 0, 0.7);
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="navbar">
    <div class="navbar-left">
        <a href="index.jsp">Home</a>
        <a href="about.jsp">About</a>
        <a href="contact.jsp">Contact</a>
    </div>
    <div class="navbar-right">
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
    </div>
</div>
<div class="login-container">
    <h2>Login</h2>
    <form action="login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="Login">
        <input type ="text">

    </form>
    <p>
        Haven't signed up?
        <a
                href = "register.jsp"
                style="color: blue; cursor: pointer"
        >
            Sign Up
        </a>
    </p>
    <c:if test="${not empty message}">
        <p style="color:red;">${message}</p>
    </c:if>
</div>
<div class="footer">
    &copy; 2024 Your Website. All rights reserved.
</div>
</body>
</html>
