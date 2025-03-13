<%@ page language="java" contentType="text/html; charset=US-ASCII"
       pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome | X-Workz</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Page Background */
        body {
            background-color: #808080; /* Grey background */
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            margin: 0;
        }

        /* Navbar */
        .navbar {
            width: 100%;
            position: absolute;
            top: 0;
        }

        .navbar-brand img {
            height: 50px;
        }

        /* Container Styling */
        .container-box {
            background: black; /* Black container */
            color: white;
            border-radius: 15px;
            padding: 40px;
            text-align: center;
            width: 90%;
            max-width: 600px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
        }

        /* Button Styling */
        .btn-custom {
            margin-top: 20px;
            font-size: 1.2rem;
            padding: 10px 20px;
            border-radius: 25px;
            background: #ff6600;
            color: white;
            border: none;
            transition: all 0.3s ease-in-out;
        }

        .btn-custom:hover {
            background: #e65c00;
            transform: scale(1.05);
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
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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
                </ul>
            </div>
        </div>
    </nav>

    <!-- Welcome Message -->
    <div class="container-box">
        <h2>Welcome to X-Workz!</h2>
        <p>Your journey into the world of technology begins here.</p>
        <a href="signup.jsp" class="btn btn-custom">Get Started</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
