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
        <th scope="col">Registration date</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="employee" items="${employees}">

        <c:choose>
            <%--@elvariable id="flag" type="java.lang.String"--%>
            <c:when test="${''.concat(employee.ID) eq flag}">
                <c:set var="currLogin" value="${login}"/>
                <c:set var="currEmail" value="${email}"/>
                <c:set var="currRank" value="${rank}"/>
                <c:set var="currDate" value="${date}"/>
                <c:set var="isError" value="true"/>
            </c:when>
            <c:otherwise>
                <c:set var="currLogin" value="${employee.login}"/>
                <c:set var="currEmail" value="${employee.email}"/>
                <c:set var="currRank" value="${employee.rank}"/>
                <c:set var="currDate" value="${employee.getDateString()}"/>
                <c:set var="isError" value="false"/>
            </c:otherwise>
        </c:choose>


        <tr>
            <form method="post">
                <input type="hidden" name="action-post" value="employee edit" autocomplete="off">

                <th scope="row">${employee.ID}
                    <input type="hidden" name="id" value="${employee.ID}" autocomplete="off">
                </th>
                <td>
                    <input type="text" class="form-control" name="new login" minlength="6" maxlength="20"
                           placeholder="Enter login" pattern="^[a-z0-9_-]{5,21}$"
                           value="${currLogin}" autocomplete="off" required>
                </td>
                <td>
                    <input type="email" class="form-control" name="new email" minlength="6" maxlength="40"
                           aria-describedby="emailHelp"
                           placeholder="Enter email"
                           value="${currEmail}" pattern='[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$' autocomplete="off"
                           required>
                    <div style="color: red" <c:if test="${not isError}">hidden</c:if>>
                        Employee email should be unique!
                    </div>
                </td>
                <td>
                    <input type="number" min="1" max="1000" class="form-control" placeholder="Enter rank"
                           name="new rank"
                           value="${currRank}" autocomplete="off" required>
                </td>
                <td>
                    <input type="date" class="form-control" min="1979-12-31" max="2079-12-31" name="new date"
                           value="${currDate}" autocomplete="off" required>
                </td>

                <td>
                    <button type="submit" class="btn btn-warning">Edit</button>
                </td>
            </form>
            <form method="post">
                <td>
                    <input type="hidden" name="id" value="${employee.ID}" autocomplete="off">
                    <input type="hidden" name="action-post" value="employee delete" autocomplete="off">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </td>
            </form>
        </tr>
    </c:forEach>
    <tr>
        <form id="add" method="post">
            <input type="hidden" name="action-post" value="add new employee" autocomplete="off">
            <th>*</th>

            <c:choose>
                <%--@elvariable id="flag" type="java.lang.String"--%>
                <c:when test="${'invalid-new-employee' eq flag}">
                    <c:set var="currLogin" value="${login}"/>
                    <c:set var="currEmail" value="${email}"/>
                    <c:set var="currRank" value="${rank}"/>
                    <c:set var="currDate" value="${date}"/>
                    <c:set var="isError" value="true"/>
                </c:when>
                <c:otherwise>
                    <c:set var="currLogin" value=""/>
                    <c:set var="currEmail" value=""/>
                    <c:set var="currRank" value=""/>
                    <c:set var="currDate" value=""/>
                    <c:set var="isError" value="false"/>
                </c:otherwise>
            </c:choose>

            <td>
                <input type="text" class="form-control" name="new login" minlength="6" maxlength="20"
                       placeholder="Enter login"
                       value="${currLogin}" pattern="^[a-z0-9_-]{5,21}$" autocomplete="off" required>
            </td>
            <td>
                <input type="email" class="form-control" name="new email" minlength="6" maxlength="40"
                       aria-describedby="emailHelp"
                       placeholder="Enter email" value="${currEmail}"
                       pattern='[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$' autocomplete="off" required>
                <div style="color: red" <c:if test="${not isError}">hidden</c:if>>
                    New employee email should be unique!
                </div>
            </td>
            <td>
                <input type="number" name="new rank" min="1" max="1000" class="form-control"
                       placeholder="Enter rank"
                       value="${currRank}" autocomplete="off" required>
            </td>
            <td>
                <input type="date" class="form-control" min="1979-12-31" max="2079-12-31" name="new date"
                       value="${currDate}" autocomplete="off" required>
            </td>

            <td>
                <button type="submit" class="btn btn-success">Add</button>
            </td>
            <td>

            </td>
        </form>
    </tr>

    </tbody>
</table>
<span class="d-block p-2 bg-dark text-white" style="margin: 0">
     <form id="back" method="get" style="margin-top: 5px">
     <button type="submit" class="btn btn-primary">< BACK</button>
    </form>
</span>

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
