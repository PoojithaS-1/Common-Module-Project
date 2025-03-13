<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #808080; /* Grey background */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            flex-direction: column;
        }

        /* Navbar */
        .navbar {
            width: 100%;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
        }

        .navbar-brand img {
            height: 50px;
        }

        /* Container Styling */
        .container {
            background: #000; /* Black container */
            color: white; /* White text */
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 450px;
            margin-top: 80px; /* To avoid overlap with navbar */
        }

        input, button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            background-color: red;
            color: white;
            cursor: pointer;
            border: none;
        }

        button:hover {
            background-color: darkred;
        }

        .message {
            margin-top: 10px;
            color: green;
        }

        .error {
            color: red;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://www.x-workz.in/Logo.png" alt="Logo">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="signup.jsp">Sign Up</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="signin.jsp">Sign In</a>
                </li>
                <li class="nav-item">
                                    <a class="nav-link" href="logout.jsp">Logout</a>
                               </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Delete User Form -->
<div class="container">
    <h2>Delete User Account</h2>
    <form action="deleteUser" method="post">
        <label for="email">Enter Your Email:</label>
        <input type="email" id="email" name="email" required>
        <button type="submit">Delete Account</button>
    </form>

    <!-- Success or Error Messages -->
    <c:if test="${not empty successMessage}">
        <p class="message">${successMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p class="error">${errorMessage}</p>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
