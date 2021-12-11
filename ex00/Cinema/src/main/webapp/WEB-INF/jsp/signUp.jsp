<%--
  Created by IntelliJ IDEA.
  User: romankukin
  Date: 11.12.2021
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>

<h1>Registration</h1>
<form action="${pageContext.request.contextPath}/signUp" method="post">
    <div>
        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="Enter email" required>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter password" required>
    </div>
    <div>
        <label for="first-name">First Name</label>
        <input type="text" id="first-name" name="first-name" placeholder="Enter first name">
    </div>
    <div>
        <label for="last-name">Last Name</label>
        <input type="text" id="last-name" name="last-name" placeholder="Enter last name">
    </div>
    <div>
        <label for="phone-number">Phone Number</label>
        <input type="tel" id="phone-number" name="phone-number" placeholder="Enter phone number">
    </div>
    <div>
        <button type="submit">Register</button>
    </div>
</form>

</body>
</html>
