<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%
    // Invalidate the session
    session.invalidate();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
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

        /* Container Styling */
        .container {
            background: #000; /* Black container */
            color: white; /* White text */
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 400px;
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

        .btn-home {
            background-color: green;
        }

        .btn-home:hover {
            background-color: #0056b3;
        }

        .btn-signin {
            background-color: #dc3545;
        }

        .btn-signin:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>You have been logged out</h2>
    <p>Thank you for using our Application...!</p>

    <a href="index.jsp" class="btn-custom btn-home">Go to Home</a>
    <a href="signin.jsp" class="btn-custom btn-signin">Sign In </a>
</div>

</body>
</html>
