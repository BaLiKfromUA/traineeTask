<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>BaLiK Sandbox</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

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

    <c:set var="departmentId" value='<%= request.getParameter("department_id") %>'/>
    <c:forEach var="employee" items="${employees}">

        <c:choose>
            <%--@elvariable id="flag" type="java.lang.String"--%>
            <c:when test="${''.concat(employee.ID) eq flag}">
                <c:set var="currLogin" value="${login}"/>
                <c:set var="currEmail" value="${email}"/>
                <c:set var="currRank" value="${rank}"/>
                <c:set var="currDate" value="${date}"/>
            </c:when>
            <c:otherwise>
                <c:set var="currLogin" value="${employee.login}"/>
                <c:set var="currEmail" value="${employee.email}"/>
                <c:set var="currRank" value="${employee.rank}"/>
                <c:set var="currDate" value="${employee.getDateString()}"/>
            </c:otherwise>
        </c:choose>


        <tr>
                <%--@elvariable id="employeeModel" type="com.aimprosoft.sandbox.controller.data.EmployeeData"--%>
            <form:form method="post" action="/employees/edit?department_id=${departmentId}"
                       modelAttribute="employeeModel">

                <th scope="row">${employee.ID}
                    <form:hidden path="departmentId" value="${departmentId}"/>
                    <form:input path="id" type="hidden" name="id" value="${employee.ID}" autocomplete="off"/>
                </th>
                <td>
                    <form:input path="login" type="text" class="form-control" minlength="6" maxlength="20"
                                placeholder="Enter login" pattern="^[a-z0-9_-]{5,21}$"
                                value="${currLogin}" autocomplete="off" required="true"/>
                </td>
                <td>
                    <form:input path="email" type="email" class="form-control" minlength="6" maxlength="40"
                                aria-describedby="emailHelp"
                                placeholder="Enter email"
                                value="${currEmail}"
                                pattern="^([0-9a-z]([-_\\.]*[0-9a-z]+)*)@([0-9a-z]([-_\\.]*[0-9a-z]+)*)[\\.]([a-z]{2,6})$"
                                autocomplete="off"
                                required="true"/>
                </td>
                <td>
                    <form:input path="rank" type="number" min="1" max="999" class="form-control"
                                placeholder="Enter rank"
                                value="${currRank}" autocomplete="off" required="true"/>
                </td>
                <td>
                    <form:input path="date" type="date" class="form-control"
                                min="1979-12-31" max="2079-12-31"
                                value="${currDate}" autocomplete="off" required="true"/>
                </td>

                <td>
                    <button type="submit" class="btn btn-warning">Edit</button>
                </td>
            </form:form>
            <form method="post" action="/employees/delete?department_id=${departmentId}">
                <td>
                    <input type="hidden" name="id" value="${employee.ID}" autocomplete="off">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </td>
            </form>
        </tr>
    </c:forEach>
    <tr>
        <%--@elvariable id="employeeModel" type="com.aimprosoft.sandbox.controller.data.EmployeeData"--%>
        <form:form id="add" method="post"
                   action="/employees/add?department_id=${departmentId}"
                   modelAttribute="employeeModel">
            <th>*</th>

            <c:choose>
                <%--@elvariable id="flag" type="java.lang.String"--%>
                <c:when test="${'invalid-new-employee' eq flag}">
                    <c:set var="currLogin" value="${login}"/>
                    <c:set var="currEmail" value="${email}"/>
                    <c:set var="currRank" value="${rank}"/>
                    <c:set var="currDate" value="${date}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="currLogin" value=""/>
                    <c:set var="currEmail" value=""/>
                    <c:set var="currRank" value=""/>
                    <c:set var="currDate" value=""/>
                </c:otherwise>
            </c:choose>

            <td>
                <form:hidden path="departmentId" value="${departmentId}"/>
                <form:input path="login" type="text" class="form-control" minlength="6" maxlength="20"
                            placeholder="Enter login"
                            value="${currLogin}" pattern="^[a-z0-9_-]{5,21}$" autocomplete="off" required="true"/>
            </td>
            <td>
                <form:input path="email" type="email" class="form-control" minlength="6"
                            maxlength="40"
                            aria-describedby="emailHelp"
                            placeholder="Enter email" value="${currEmail}"
                            pattern="^([0-9a-z]([-_\\.]*[0-9a-z]+)*)@([0-9a-z]([-_\\.]*[0-9a-z]+)*)[\\.]([a-z]{2,6})$"
                            autocomplete="off" required="true"/>
            </td>
            <td>
                <form:input path="rank" type="number" min="1" max="999" class="form-control"
                            placeholder="Enter rank"
                            value="${currRank}" autocomplete="off" required="true"/>
            </td>
            <td>
                <form:input path="date" type="date" class="form-control"
                            min="1979-12-31" max="2079-12-31"
                            value="${currDate}" autocomplete="off" required="true"/>
            </td>

            <td>
                <button type="submit" class="btn btn-success">Add</button>
            </td>
            <td>

            </td>
        </form:form>
    </tr>

    </tbody>
</table>
<span class="d-block p-2 bg-dark text-white" style="margin: 0">
     <form id="back" method="get" action="/departments" style="margin-top: 5px">
     <button type="submit" class="btn btn-primary">< BACK</button>
    </form>
</span>
<div class="alert alert-danger" role="alert" <c:if test="${not dbError}">hidden</c:if>>
    ${errorMessage}
</div>

<div style="margin: 0" class="alert alert-warning" role="alert"
     <c:if test="${not 'invalid'.equals(reason)}">hidden</c:if>>
    <h4 class="alert-heading">Invalid input data!</h4>
    <c:forEach var="message" items="${errorMessages}">
        <p>${message}</p>
        <hr>
    </c:forEach>
</div>
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
