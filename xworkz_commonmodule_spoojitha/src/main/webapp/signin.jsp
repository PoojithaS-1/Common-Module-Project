<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Full-page grey background */
        body {
            font-family: Arial, sans-serif;
            background-color: #808080;
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Centered Form Container */
        .form-container {
            background: #000; /* Black background */
            padding: 30px;
            border-radius: 10px;
            width: 400px;
            color: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
        }

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

        label {
            font-weight: bold;
            color: white;
        }

        .form-control {
            background-color: rgba(255, 255, 255, 0.1);
            color: white;
            border: 1px solid #ccc;
        }

        .form-control::placeholder {
            color: #ddd;
        }

        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }

        .register-link {
            display: block;
            text-align: center;
            margin-top: 10px;
            font-size: 14px;
        }

        .register-link a {
            color: lightblue;
            text-decoration: none;
            font-weight: bold;
        }

        .register-link a:hover {
            text-decoration: underline;
        }

        .btn-primary {
            background-color: #28a745;
            border: none;
            font-weight: bold;
        }

        .btn-primary:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://www.x-workz.in/Logo.png" alt="Logo">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Logout</a>
                </li>
                <li class="nav-item">
                                    <a class="nav-link" href="signup.jsp">SignUp</a>
                                </li>
<li class="nav-item">
                    <a class="nav-link" href="deleteUser.jsp">Delete</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Centered Sign-In Form -->
<div class="form-container">
    <h2 class="text-center">Sign In</h2>

    <c:if test="${not empty error}">
        <p class="error-message text-center">${error}</p>
    </c:if>

<form action="signIn" method="post">
    <div class="mb-3">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" class="form-control" value="${param.email}" required>
    </div>

    <div class="mb-3">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" class="form-control" required>
    </div>

    <!-- CAPTCHA Display -->
    <div class="mb-3 text-center">
        <label for="captcha">Enter CAPTCHA: <strong>${sessionScope.captcha}</strong></label>
        <input type="text" id="captcha" name="captcha" class="form-control" required>
    </div>

    <div class="text-center">
        <input type="submit" value="SIGN IN" class="btn btn-primary w-100">
    </div>
</form>

<!-- Register Link -->
        <a href="signup.jsp" class="register-link">Don't have an account? Register here</a>
        <p class="register-link">Forgot password? <a href="forgot-password.jsp">Forgot password</a></p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
