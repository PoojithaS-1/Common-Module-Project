<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #808080; /* Grey background */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .error-container {
            background: #000; /* Black container */
            color: white; /* White text */
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 300px;
        }
        .error-message {
            color: #ff4d4d; /* Light red error message */
            font-size: 18px;
            font-weight: bold;
        }
        .back-link, .signup-btn {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 15px;
            border-radius: 5px;
            font-size: 16px;
            text-decoration: none;
            cursor: pointer;
        }
        .back-link {
            background-color: #1e90ff; /* Light blue */
            color: white;
        }
        .signup-btn {
            background-color: #28a745; /* Green */
            color: white;
        }
        .back-link:hover {
            background-color: #0b6fc2;
        }
        .signup-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <p class="error-message">${error}</p>
        <a href="signup.jsp" class="signup-btn">Try Again</a>
    </div>
</body>
</html>
