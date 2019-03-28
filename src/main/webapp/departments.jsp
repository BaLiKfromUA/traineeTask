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
    <%--@elvariable id="departments" type="java.util.List<com.aimprosoft.sandbox.database.department.Department>"--%>
    <c:forEach var="department" items="${departments}">

        <c:set var="departmentName">
            <c:choose>
                <%--@elvariable id="flag" type="java.lang.String"--%>
                <c:when test="${''.concat(department.ID) eq flag}">
                    <%--@elvariable id="name" type="java.lang.String"--%>
                    ${name}
                    <c:set var="isError" value="true"/>
                </c:when>
                <c:otherwise>
                    ${department.name}
                    <c:set var="isError" value="false"/>
                </c:otherwise>
            </c:choose>
        </c:set>

        <tr>
            <form method="post">
                <input type="hidden" name="action-post" value="department edit" autocomplete="off">
                <th scope="row">${department.ID}
                    <input type="hidden" name="id" value="${department.ID}" autocomplete="off"></th>
                <td>
                    <input type="text" name="new name" class="form-control" minlength="6" maxlength="128"
                           placeholder="Enter department name"
                           value="${departmentName}" autocomplete="off" required>

                    <div style="color: red" <c:if test="${not isError}">hidden</c:if>>
                        New department name should be unique!
                    </div>

                </td>
                <td>
                    <button type="submit" class="btn btn-warning">Edit</button>
                </td>
            </form>

            <form method="post">
                <td>
                    <button type="submit" class="btn btn-danger">Delete</button>
                    <input type="hidden" name="action-post" value="department delete" autocomplete="off">
                    <input type="hidden" name="id" value="${department.ID}" autocomplete="off">
                </td>
            </form>

            <form method="get">
                <td>
                    <button type="submit" class="btn btn-info">Employee List</button>
                    <input type="hidden" name="action-get" value="employees" autocomplete="off">
                    <input type="hidden" name="department_id" value="${department.ID}" autocomplete="off">
                </td>
            </form>

        </tr>
    </c:forEach>

    <tr>
        <form id="add" method="post">
            <input type="hidden" name="action-post" value="add new department" autocomplete="off">
            <th>*</th>
            <td>
                <c:set var="departmentName">
                    <c:choose>
                        <%--@elvariable id="flag" type="java.lang.String"--%>
                        <c:when test="${'invalid-new-department' eq flag}">
                            <%--@elvariable id="name" type="java.lang.String"--%>
                            ${name}
                            <c:set var="isError" value="true"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="isError" value="false"/>
                        </c:otherwise>
                    </c:choose>
                </c:set>

                <input type="text" class="form-control" name="new name" minlength="6" maxlength="128"
                       placeholder="Enter new department name" value="${departmentName}" autocomplete="off" required>
                <div style="color: red" <c:if test="${not isError}">hidden</c:if>>
                    New department name should be unique!
                </div>
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

<footer>
       © 2019 Copyright: <b>BaLiK</b>
</footer>

</body>
<style>
    body {
        min-height: 100vh;
        position: relative;
        margin: 0;
    }

    footer {
        position: absolute;
        bottom: 0;
        right: 50%;
    }

</style>
</html>

