<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #808080; /* Grey background */
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
        }

        .form-container {
            background: #000; /* Black container */
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            width: 400px;
        }

        h2 {
            color: #fff;
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            color: #ddd;
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            text-align: left; /* Left-align label */
        }

        .form-control {
            background-color: #333;
            color: white;
            border: 1px solid #555;
        }

        .form-control:focus {
            background-color: #444;
            color: white;
            border-color: #007bff;
        }

        .btn-custom {
            background-color: #007bff;
            color: white;
            width: 100%;
            border: none;
            padding: 10px;
            border-radius: 5px;
            font-size: 16px;
            margin-top: 10px;
        }

        .btn-custom:hover {
            background-color: #0056b3;
        }

        .text-center a {
            color: #1e90ff;
            text-decoration: none;
        }

        .text-center a:hover {
            text-decoration: underline;
        }

        .alert {
            text-align: left;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Forgot Password</h2>

    <!-- Display error message if password reset fails -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Display success message if password reset is successful -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>

    <form action="forgotPassword" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Enter your Email : </label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="newPassword" class="form-label">New Password : </label>
            <input type="password" id="newPassword" name="newPassword" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-custom">Reset Password</button>
    </form>

    <div class="text-center mt-3">
        <a href="signin.jsp">Back to Login</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
