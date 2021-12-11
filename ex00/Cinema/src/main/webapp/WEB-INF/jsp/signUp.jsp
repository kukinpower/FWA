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

    <label for="email">Email</label>
    <input type="email" id="email" name="email" placeholder="Enter email">

    <label for="password">Password</label>
    <input type="password" id="password" name="password" placeholder="Enter password">

    <div>
        <button type="submit">Register</button>
    </div>
</form>

</body>
</html>
