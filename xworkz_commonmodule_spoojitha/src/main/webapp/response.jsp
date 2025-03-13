<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Success</title>
    <style>
        /* Page Background */
        body {
            background-color: grey; /* Grey background */
            font-family: Arial, sans-serif;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Centered Container */
        .container-box {
            background-color: black; /* Black container */
            color: white;
            text-align: center;
            padding: 30px;
            border-radius: 10px;
            width: 80%;
            max-width: 500px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
        }

        h1 {
            font-size: 28px;
            font-weight: bold;
            color: green;
            margin-bottom: 20px;
        }

        h3 {
            font-size: 20px;
            font-weight: bold;
            color: blue;
            margin: 20px 0;
        }

        h4 {
            color: red;
            font-size: 16px;
            font-weight: bold;
        }

        /* Button Styling */
        .redirect-btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
            text-decoration: none;
            display: inline-block;
        }

        .redirect-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container-box">
        <h1>Thank you, ${name}, for Registering!!</h1>


        <h4>${errors}</h4>

        <!-- Redirect Button to Signup Page -->

        <a href="signin.jsp" class="redirect-btn">SIGN IN</a>
    </div>

</body>
</html>
