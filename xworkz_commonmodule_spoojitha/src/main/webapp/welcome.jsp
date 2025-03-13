<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Grey Background */
        body {
            font-family: Arial, sans-serif;
            background-color: #808080;
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
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

        /* Black Container */
        .welcome-container {
            background: #000; /* Black background */
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            text-align: center;
            max-width: 500px;
            width: 90%;
            color: white; /* White text for contrast */
        }

        .btn-custom {
            display: inline-block;
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            text-decoration: none;
            color: white;
            margin-top: 10px;
            transition: background 0.3s ease-in-out;
        }

        .btn-update {
            background-color: green;
        }

        .btn-update:hover {
            background-color: #0056b3;
        }

        .btn-logout {
            background-color: #dc3545;
        }

        .btn-logout:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<!-- Navigation Bar -->
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
                    <a class="nav-link" href="signup.jsp">SignUp</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="deleteUser.jsp">Delete</a> <!-- Added Delete User Link -->
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Logout</a> <!-- Added Logout Link -->
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Welcome Message -->
<div class="welcome-container">
    <h2>Welcome ${user.name}!</h2>
    <p>You have successfully logged in!</p>

    <!-- Update Profile Button -->
    <a href="update-profile.jsp" class="btn-custom btn-update">Update Profile</a>
    <!-- Logout Button -->
    <a href="index.jsp" class="btn-custom btn-logout">Go Back To Home</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
