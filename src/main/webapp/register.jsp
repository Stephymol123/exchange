<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f0f2f5;
        }
        .navbar {
            background-color: #007bff;
            color: white;
            padding: 10px;
            position: fixed;
            top: 0;
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            z-index: 1000;
        }
        .navbar a {
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            transition: background-color 0.3s;
        }
        .navbar a:hover {
            background-color: #0056b3;
        }
        .navbar-right {
            display: flex;
            align-items: center;
        }
        .register-container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin: auto;
            margin-top: 80px;
        }
        .register-container h2 {
            margin-bottom: 20px;
            text-align: center;
        }
        .register-container label {
            display: block;
            margin-bottom: 5px;
        }
        .register-container input[type="text"],
        .register-container input[type="password"],
        .register-container input[type="email"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .register-container button[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .register-container button[type="submit"]:hover {
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
<div class="register-container">
    <h2>Register</h2>
    <form action="register" method="post">
        <label for="firstname">Firstname:</label>
        <input type="text" id="firstname" name="firstname" required>

        <label for="lastname">Lastname:</label>
        <input type="text" id="lastname" name="lastname" required>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <button type="submit">Register</button>
    </form>
    <p>
        Already have an account?
        <a href="login.jsp" style="color: blue; cursor: pointer;">Login</a>
    </p>
</div>
<div class="footer">
    &copy; 2024 Your Website. All rights reserved.
</div>
</body>
</html>
