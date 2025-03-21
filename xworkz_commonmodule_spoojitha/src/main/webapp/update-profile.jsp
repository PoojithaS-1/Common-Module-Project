<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #808080; /* Grey background */
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh; /* Ensures full-page height */
        }

        .form-container {
            background: #000; /* Black background */
            color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(255, 255, 255, 0.2);
            width: 500px;
            text-align: left;
        }

        .text-center {
            text-align: center;
        }

        label {
            font-weight: bold;
            display: block;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
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


        .btn-primary:hover {
            background-color: #0056b3;
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
                    <a class="nav-link" href="signin.jsp">SignIn</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="container mt-5">
    <div class="form-container">
        <h2 class="text-center">Update Profile</h2>


        <c:if test="${not empty successMessage}">
            <p class="success-message">${successMessage}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p class="error-message">${error}</p>
        </c:if>

        <form action="updateUser" method="post">
            <input type="hidden" name="email" value="${loggedInUser.email}" />

            <div class="mb-3">
                <label>Name:</label>
                <input type="text" name="name" class="form-control" value="${loggedInUser.name}" required />
            </div>

            <div class="mb-3">
                <label>Phone:</label>
                <input type="text" name="phoneNumber" class="form-control" value="${loggedInUser.phoneNumber}" required />
            </div>

            <div class="mb-3">
                <label>Location:</label>
                <select name="location" class="form-control" required>
                 <option value="" disabled selected>Select Location</option>
                   <option value="BANGALORE" ${loggedInUser.location == 'BANGALORE' ? 'selected' : ''}>Bangalore</option>
                                   <option value="MUMBAI" ${loggedInUser.location == 'MUMBAI' ? 'selected' : ''}>Mumbai</option>
                                   <option value="DELHI" ${loggedInUser.location == 'DELHI' ? 'selected' : ''}>Delhi</option>
                                   <option value="CHENNAI" ${loggedInUser.location == 'CHENNAI' ? 'selected' : ''}>Chennai</option>
                                   <option value="HYDERABAD" ${loggedInUser.location == 'HYDERABAD' ? 'selected' : ''}>Hyderabad</option>
                                   <option value="PUNE" ${loggedInUser.location == 'PUNE' ? 'selected' : ''}>Pune</option>
                                    <option value="KOLKATA" ${loggedInUser.location == 'KOLKATA' ? 'selected' : ''}>Kolkata</option>                                   <option value="AHMEDABAD" ${loggedInUser.location == 'AHMEDABAD' ? 'selected' : ''}>Ahmedabad</option>
                                   <option value="JAIPUR" ${loggedInUser.location == 'JAIPUR' ? 'selected' : ''}>Jaipur</option>
                                   <option value="CHANDIGARH" ${loggedInUser.location == 'CHANDIGARH' ? 'selected' : ''}>Chandigarh</option>
                                   <option value="LUCKNOW" ${loggedInUser.location == 'LUCKNOW' ? 'selected' : ''}>Lucknow</option>
                                   <option value="COIMBATORE" ${loggedInUser.location == 'COIMBATORE' ? 'selected' : ''}>Coimbatore</option>
                                   <option value="KOCHI" ${loggedInUser.location == 'KOCHI' ? 'selected' : ''}>Kochi</option>
                </select>
            </div>

            <div class="mb-3">
                <label>Age:</label>
                <input type="number" name="age" class="form-control" value="${loggedInUser.age}" />
            </div>

            <div class="mb-3">
                <label>New Password:</label>
                <input type="password" name="password" class="form-control" />
            </div>

            <div class="text-center">
                <input type="submit" value="Update Profile" class="btn btn-primary">
            </div>
        </form>
    </div>
</div>
</body>
</html>