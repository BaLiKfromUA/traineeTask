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
    <h1 style="color:white">Employees</h1>
</nav>
<table class="table table-striped table-dark" style="margin: 0">
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
                <input type="hidden" name="action-post" id="action ${employee.ID}">

                <th scope="row">${employee.ID}
                    <input type="hidden" name="id" value="${employee.ID}">
                </th>
                <td>
                    <input type="text" class="form-control" name="login" maxlength="512" placeholder="Enter login"
                           value="${employee.login}">
                </td>
                <td>
                    <input type="email" class="form-control" name="email" maxlength="512" aria-describedby="emailHelp"
                           placeholder="Enter email"
                           value=" ${employee.email}">
                    <input type="hidden" name="login" value="${employee.email}">
                </td>
                <td>
                    <input type="number" name="rank" max="1000" class="form-control" placeholder="Enter rank"
                           value="${employee.rank}">
                </td>
                <td>
                    <input type="date" class="form-control" name="date" value="${employee.dateString}">
                </td>
                <td>
                    <button type="button" class="btn btn-warning">Edit</button>
                </td>
                <td>
                    <button type="button" onclick="doDelete(${employee.ID})" class="btn btn-danger">Delete</button>
                </td>
            </form>
        </tr>
    </c:forEach>

    </tbody>
</table>
<span class="d-block p-2 bg-dark text-white" style="margin: 0">
    <button type="button" class="btn btn-success">Add</button>
     <form id="back" method="get" style="margin-top: 5px">
     <button type="button" class="btn btn-primary" onclick="document.getElementById('back').submit()">< BACK</button>
    </form>
</span>
<footer class="page-footer font-small unique-color-dark pt-4">
    <div class="footer-copyright text-center py-3">© 2019 Copyright:
        <b>BaLiK</b>
    </div>
</footer>


<script>
    function doDelete(id) {
        document.getElementById('action ' + id).value = "employee delete";
        document.getElementById('post ' + id).submit();
    }
</script>
</body>
<style>

</style>
</html>
