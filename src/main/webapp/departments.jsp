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
    <h1 style="color:white">Departments</h1>
</nav>
<table class="table table-striped table-dark" style="margin: 0">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="department" items="${departments}">
        <tr>
            <form method="post">
                <th scope="row">${department.ID}</th>
                <td>
                    <input type="text" class="form-control" maxlength="128" placeholder="Enter department name"
                           value="${department.name}">
                </td>
                <td>
                    <button type="button" class="btn btn-warning">Edit</button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger">Delete</button>
                </td>
            </form>
            <form id="getform ${department.ID}" method="get">
                <td>
                    <input type="hidden" name="action-get" value="goto">
                    <input type="hidden" name="department_id" value="${department.ID}">
                    <button type="button" onclick="document.getElementById('getform ${department.ID}').submit()"
                            class="btn btn-info">Employee List
                    </button>
                </td>
            </form>
        </tr>
    </c:forEach>
    <tr>
        <form id="add" method="post">
            <input type="hidden" name="action-post" value="add new department">
            <th>*</th>
            <td>
                <input type="text" class="form-control" name="new name" maxlength="128" placeholder="Enter new department name">
            </td>
            <td>
                <button type="button" class="btn btn-success"  onclick="document.getElementById('add').submit()">Add</button>
            </td>
            <td>

            </td>
        </form>
    </tr>
    </tbody>
</table>

<footer class="page-footer font-small unique-color-dark pt-4">
    <div class="footer-copyright text-center py-3">© 2019 Copyright:
        <b>BaLiK</b>
    </div>
</footer>

<script>

</script>
</body>
</html>
