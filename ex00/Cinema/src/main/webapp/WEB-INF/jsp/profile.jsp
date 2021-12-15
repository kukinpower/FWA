<%--
  Created by IntelliJ IDEA.
  User: romankukin
  Date: 15.12.2021
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
<table>
    <tr>
        <th>DateTime</th>
        <th>Type</th>
        <th>IP</th>
    </tr>
    <c:forEach items="${authEvents}" var="authEvents" varStatus="status">
        <tr>
            <td>${authEvents.eventTime}</td>
            <td>${authEvents.eventType}</td>
            <td>${authEvents.ipAddress}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
