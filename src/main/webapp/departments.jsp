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
            <form method="post" id="post ${department.ID}">
                <input type="hidden" name="action-post" id="action ${department.ID}">
                <th scope="row">${department.ID}
                    <input type="hidden" name="id" value="${department.ID}" required></th>
                <td>
                    <input type="text" name="name" class="form-control" maxlength="128"
                           placeholder="Enter department name"
                           value="${department.name}" required>
                    <c:if test="${flag.equals(''.concat(department.ID))}">
                        <div style="color: red">
                            New department name should be unique!
                        </div>
                    </c:if>
                </td>
                <td>
                    <button type="button" class="btn btn-warning" onclick="doEdit(${department.ID})">Edit</button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger" onclick="doDelete(${department.ID})">Delete</button>
                </td>
            </form>
            <form method="get">
                <td>
                    <input type="hidden" name="action-get" value="employees">
                    <input type="hidden" name="department_id" value="${department.ID}">
                    <button type="submit"
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
                <c:choose>
                    <c:when test="${flag.equals('invalid-new-name')}">
                        <input type="text" class="form-control" name="new name" maxlength="128"
                               placeholder="Enter new department name" value="${name}" required>
                        <div style="color: red">
                            New department name should be unique!
                        </div>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="form-control" name="new name" maxlength="128"
                               placeholder="Enter new department name" required>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <button type="submit" class="btn btn-success">Add</button>
            </td>
            <td>

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
    function doDelete(id) {
        document.getElementById('action ' + id).value = "department delete";
        document.getElementById('post ' + id).submit();
    }

    function doEdit(id) {
        document.getElementById('action ' + id).value = "department edit";
        document.getElementById('post ' + id).submit();
    }
</script>
</body>
</html>
