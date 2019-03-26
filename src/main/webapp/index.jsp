<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BaLiK Sandbox</title>
    <style type="text/css">
        <%@include file="bootstrap/css/bootstrap.min.css" %>
        <%@include file="bootstrap/css/bootstrap-grid.min.css" %>
        <%@include file="bootstrap/css/bootstrap-reboot.min.css" %>
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <h1 style="color:white">Employee Table</h1>
</nav>
<table class="table table-striped table-dark">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Login</th>
        <th scope="col">Email</th>
        <th scope="col">Score</th>
        <th scope="col">Reg. date</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <form method="post" id="post ${employee.ID}">
                <th scope="row">${employee.ID}</th>
                <td>
                    <input type="text" class="form-control" placeholder="Enter login" value="${employee.login}">
                </td>
                <td>
                    <input type="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email"
                           value=" ${employee.email}">
                </td>
                <td>
                    <input type="number" class="form-control" placeholder="Enter rank" value="${employee.rank}">
                </td>
                <td>
                    <input type="date" class="form-control" value="${employee.dateString}">
                </td>
                <td>
                    <button type="button" class="btn btn-warning">Edit</button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger">Delete</button>
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
<button type="button" class="btn btn-success">Add</button>
<footer class="page-footer font-small unique-color-dark pt-4">
    <div class="footer-copyright text-center py-3">© 2019 Copyright:
        <b>BaLiK</b>
    </div>
</footer>
</body>
<style>

</style>
</html>
