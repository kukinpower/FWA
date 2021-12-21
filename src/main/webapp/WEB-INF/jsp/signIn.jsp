<%--
  Created by IntelliJ IDEA.
  User: romankukin
  Date: 11.12.2021
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/signIn" method="post">
    <div>
        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="Enter email" required>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter password" required>
    </div>
    <div>
        <button type="submit">Sign in</button>
    </div>
</form>
<a href="${pageContext.request.contextPath}/">Go to main page</a>
</body>
</html>
