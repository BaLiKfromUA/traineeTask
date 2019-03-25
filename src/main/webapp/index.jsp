<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BaLiK Sandbox</title>
</head>
<body>
<h1>Employee table</h1>
<table>
    <tr>
        <td>Login</td>
        <td>Email</td>
        <td>Score</td>
        <td>Reg. date</td>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.login}</td>
            <td>${employee.email}</td>
            <td>${employee.rank}</td>
            <td>${employee.registrationDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
<style>
    table,td,tr{
        border: 1px solid;
    }
</style>
</html>
