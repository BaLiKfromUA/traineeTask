<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
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
    <%--@elvariable id="departments" type="java.util.List<com.aimprosoft.sandbox.domain.Department>"--%>
    <c:forEach var="department" items="${departments}">

        <c:set var="departmentName">
            <c:choose>
                <%--@elvariable id="flag" type="java.lang.String"--%>
                <c:when test="${''.concat(department.ID) eq flag}">
                    <%--@elvariable id="name" type="java.lang.String"--%>
                    ${incorrect_name}
                    <c:set var="isError" value="true"/>
                </c:when>
                <c:otherwise>
                    ${department.name}
                    <c:set var="isError" value="false"/>
                </c:otherwise>
            </c:choose>
        </c:set>

        <tr>
                <%--@elvariable id="departmentModel" type="com.aimprosoft.sandbox.controller.data.DepartmentData"--%>
            <form:form method="post" action="/departments/edit" modelAttribute="departmentModel">
                <th scope="row">${department.ID}
                    <form:hidden path="id" value="${department.ID}"/>
                </th>
                <td>
                    <input:input path="name" type="text" class="form-control" minlength="6"
                                 maxlength="20"
                                 placeholder="Enter department name" pattern="^[A-Z][a-z]{5,21}$"
                                 value="${departmentName}" autocomplete="off" required="true"/>
                    <div <c:if test="${not isError}">hidden</c:if>>
                        <form:errors path="name" cssStyle="color: red"/>
                    </div>
                </td>
                <td>
                    <button type="submit" class="btn btn-warning">Edit</button>
                </td>
            </form:form>

            <form method="post" action="/departments/delete">
                <td>
                    <button type="submit" class="btn btn-danger">Delete</button>
                    <input type="hidden" name="id" value="${department.ID}" autocomplete="off">
                </td>
            </form>

            <form method="get" action="/employees">
                <td>
                    <button type="submit" class="btn btn-info">Employee List</button>
                    <input type="hidden" name="department_id" value="${department.ID}" autocomplete="off">
                </td>
            </form>

        </tr>
    </c:forEach>

    <tr>
        <%--@elvariable id="departmentModel" type="com.aimprosoft.sandbox.controller.data.DepartmentData"--%>
        <form:form id="add" method="post" action="/departments/add" modelAttribute="newDepartmentModel">
            <th>*</th>
            <td>
                <c:set var="newDepartmentName">
                    <c:choose>
                        <%--@elvariable id="flag" type="java.lang.String"--%>
                        <c:when test="${'invalid-new-department' eq flag}">
                            <%--@elvariable id="name" type="java.lang.String"--%>
                            ${incorrect_name}
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </c:set>

                <form:input path="name" type="text" class="form-control" minlength="6" maxlength="20"
                            placeholder="Enter new department name" value="${newDepartmentName}" autocomplete="off"
                            pattern="^[A-Z][a-z]{5,21}$" required="false"/>


                <form:errors path="name" cssStyle="color: red"/>
            </td>
            <td>
                <button type="submit" class="btn btn-success">Add</button>
            </td>
            <td>

            </td>
            <td>

            </td>
        </form:form>
    </tr>
    </tbody>
</table>
<div class="alert alert-danger" role="alert" <c:if test="${not dbError}">hidden</c:if>>
    ${errorMessage}
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

