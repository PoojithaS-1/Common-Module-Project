<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<c:set var="email" value="${loggedInUser.email}" />

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2>Welcome, ${loggedInUser.name}!</h2>
<p>Email: ${loggedInUser.email}</p>
<p>Phone: ${loggedInUser.phoneNumber}</p>
<p>Location: ${loggedInUser.location}</p>
<p>Age: ${loggedInUser.age}</p>

<a href="updateProfile">Update Profile</a> |
</body>
</html>
