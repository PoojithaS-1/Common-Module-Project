<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
      /* Page Background */
      body {
          font-family: Arial, sans-serif;
          background-color: grey; /* Grey background */
          height: 100vh;
          margin: 0;
          display: flex;
          justify-content: center;
          align-items: center;
      }

      /* Centered Form Container */
      .form-container {
          background: black; /* Black container */
          padding: 30px;
          border-radius: 10px;
          width: 500px;
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
          color: #fff; /* White labels */
      }

      .form-control {
          background-color: #222; /* Dark field background */
          color: white;
          border: 1px solid #444;
      }

      .form-control::placeholder {
          color: #aaa;
      }

      .error-message {
          color: red;
          font-size: 12px;
      }

      .btn-success {
          background-color: #28a745;
          border: none;
          font-weight: bold;
      }

      .btn-success:hover {
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
                    <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="sign-in.jsp">Sign Up</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="signin.jsp">Sign In</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Centered Form -->
<div class="form-container">
    <h2 class="text-center">User Registration</h2>

    <c:if test="${not empty successMessage}">
        <p class="text-success text-center">${successMessage}</p>
    </c:if>

    <form action="addUser" method="post">
        <div class="row">
            <div class="col-md-6 form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" class="form-control" value="${param.name}" required>
                <c:if test="${not empty nameError}">
                    <p class="error-message">${nameError}</p>
                </c:if>
            </div>

            <div class="col-md-6 form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" class="form-control" value="${param.email}" required>
                <c:if test="${not empty emailError}">
                    <p class="error-message">${emailError}</p>
                </c:if>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${param.phoneNumber}" required>
                <c:if test="${not empty phoneError}">
                    <p class="error-message">${phoneError}</p>
                </c:if>
            </div>

            <div class="col-md-6 form-group">
                <label for="location">Location:</label>
                <select id="location" name="location" class="form-control" required>
                    <option value="">Select Location</option>
                    <option value="BANGALORE">Bangalore</option>
                    <option value="MUMBAI">Mumbai</option>
                    <option value="DELHI">Delhi</option>
                    <option value="CHENNAI">Chennai</option>
                    <option value="HYDERABAD">Hyderabad</option>
                    <option value="PUNE">Pune</option>
                    <option value="KOLKATA">Kolkata</option>
                    <option value="AHMEDABAD">Ahmedabad</option>
                    <option value="JAIPUR">Jaipur</option>
                    <option value="CHANDIGARH">Chandigarh</option>
                    <option value="LUCKNOW">Lucknow</option>
                    <option value="COIMBATORE">Coimbatore</option>
                    <option value="KOCHI">Kochi</option>
                    <option value="VISAKHAPATNAM">Visakhapatnam</option>
                    <option value="BHOPAL">Bhopal</option>
                    <option value="INDORE">Indore</option>
                    <option value="NAGPUR">Nagpur</option>
                    <option value="SURAT">Surat</option>
                    <option value="PATNA">Patna</option>
                    <option value="GUWAHATI">Guwahati</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 form-group">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender" class="form-control" required>
                    <option value="" disabled selected>Select Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>

            <div class="col-md-6 form-group">
                <label for="dOB">Date of Birth:</label>
                <input type="date" id="dOB" name="dOB" class="form-control" required>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 form-group">
                <label for="age">Age:</label>
                <input type="text" id="age" name="age" class="form-control" value="${param.age}" required>
            </div>
        </div>

        <div class="text-center mt-3">
            <input type="submit" value="SUBMIT" class="btn btn-success">
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
