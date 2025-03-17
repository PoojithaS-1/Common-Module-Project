<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
      body {
          font-family: Arial, sans-serif;
          background-color: grey;
          margin: 0;
          padding-top: 80px;
      }
      .form-container {
          background: black;
          padding: 30px;
          border-radius: 10px;
          max-width: 700px;
          margin: auto;
          box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      }
      .navbar {
          position: fixed;
          width: 100%;
          top: 0;
          left: 0;
          z-index: 1000;
      }

      .navbar-brand img {
          height: 45px;
      }
      label {
          font-weight: 600;
          color: white;
      }
      .form-control {
          border-radius: 6px;
          border: 1px solid #ddd;
      }
      .form-control:focus {
          border-color: #28a745;
          box-shadow: 0 0 5px rgba(40, 167, 69, 0.5);
      }
      .error-message {
          color: red;
          font-size: 12px;
      }
      .btn-success {
          width: 100%;
          padding: 10px;
          font-weight: bold;
          border-radius: 6px;
          background-color: blue;
      }
      .btn-success:hover {
          background-color: blue;
      }
      .text-success {
          font-weight: bold;
      }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">
            <img src="https://www.x-workz.in/Logo.png" alt="Logo">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="signin.jsp">Sign In</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="form-container">
<h3 class="text-center mb-3" style="color: white;">User Registration</h3>

    <c:if test="${not empty successMessage}">
        <p class="text-success text-center">${successMessage}</p>
    </c:if>

    <form id="registrationForm" action="addUser" method="post" onsubmit="return validateForm()">
           <div class="mb-3">
               <label for="name">Full Name:</label>
               <input type="text" id="name" name="name" class="form-control" value="${dto.name}" required>
               <p class="error-message" id="nameError">Invalid Name: Must start with an uppercase letter and contain only letters and spaces (3-50 characters).</p>

           </div>


            <div class="mb-3">
                <label for="email">Email Address:</label>
                <input type="email" id="email" name="email" class="form-control" required>
                <p class="error-message" id="emailError">Invalid Email: Must be a valid Gmail address.</p>
            </div>

            <div class="mb-3">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" required>
                <p class="error-message" id="phoneError">Invalid Phone Number: Must be a 10-digit number starting with 9, 8, 7, or 6.</p>
            </div>

            <div class="mb-3">
                <label for="age">Age:</label>
                <input type="number" id="age" name="age" class="form-control" required>
                <p class="error-message" id="ageError">Age must be between 1 and 100.</p>
            </div>
            <div class="mb-3">
                        <label for="location">Location:</label>
                        <select id="location" name="location" class="form-control" required>
                            <option value="">Select Location</option>
                            <c:forEach var="city" items="${['BANGALORE','MUMBAI','DELHI','CHENNAI','HYDERABAD','PUNE','KOLKATA','AHMEDABAD','JAIPUR','CHANDIGARH','LUCKNOW','COIMBATORE','KOCHI','VISAKHAPATNAM','BHOPAL','INDORE','NAGPUR','SURAT','PATNA','GUWAHATI']}">
                                <option value="${city}" <c:if test="${location == city}">selected</c:if>>${city}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="gender">Gender:</label>
                        <select id="gender" name="gender" class="form-control" required>
                            <option value="">Select Gender</option>
                            <option value="Male" <c:if test="${gender == 'Male'}">selected</c:if>>Male</option>
                            <option value="Female" <c:if test="${gender == 'Female'}">selected</c:if>>Female</option>
                            <option value="Other" <c:if test="${gender == 'Other'}">selected</c:if>>Other</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="dOB">Date of Birth:</label>
                        <input type="date" id="dOB" name="dOB" class="form-control" value="${dOB}" required>
                    </div>

            <div class="mt-4">
                <button type="submit" class="btn btn-success">REGISTER</button>
            </div>
        </form>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            // Hide all error messages initially
            document.querySelectorAll(".error-message").forEach(error => {
                error.style.display = "none";
            });

            // Validation function
            function validateForm() {
                let isValid = true;

                const name = document.getElementById("name").value.trim();
                const email = document.getElementById("email").value.trim();
                const phone = document.getElementById("phoneNumber").value.trim();
                const age = document.getElementById("age").value.trim();

                const nameRegex = /^[A-Z][a-zA-Z ]{2,49}$/;
                const emailRegex = /^[a-zA-Z0-9._]+@gmail\.com$/;
                const phoneRegex = /^[9876]\d{9}$/;

                function showError(id, condition) {
                    const errorElement = document.getElementById(id);
                    if (condition) {
                        errorElement.style.display = "block";
                        isValid = false;
                    } else {
                        errorElement.style.display = "none";
                    }
                }

                showError("nameError", !nameRegex.test(name));
                showError("emailError", !emailRegex.test(email));
                showError("phoneError", !phoneRegex.test(phone));
                showError("ageError", !(age >= 1 && age <= 100));

                return isValid;
            }

            // Attach real-time validation for instant feedback
            document.getElementById("name").addEventListener("input", function () {
                document.getElementById("nameError").style.display = /^[A-Z][a-zA-Z ]{2,49}$/.test(this.value.trim()) ? "none" : "block";
            });

            document.getElementById("email").addEventListener("input", function () {
                document.getElementById("emailError").style.display = /^[a-zA-Z0-9._]+@gmail\.com$/.test(this.value.trim()) ? "none" : "block";
            });

            document.getElementById("phoneNumber").addEventListener("input", function () {
                document.getElementById("phoneError").style.display = /^[9876]\d{9}$/.test(this.value.trim()) ? "none" : "block";
            });

            document.getElementById("age").addEventListener("input", function () {
                const ageValue = parseInt(this.value.trim(), 10);
                document.getElementById("ageError").style.display = (ageValue >= 1 && ageValue <= 100) ? "none" : "block";
            });

            // Attach validation on form submission
            document.getElementById("registrationForm").onsubmit = validateForm;
        });
    </script>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
