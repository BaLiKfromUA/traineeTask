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
        <tr>
            <form method="post" id="post ${employee.ID}">
                <input type="hidden" name="action-post" id="action ${employee.ID}">

                <th scope="row">${employee.ID}
                    <input type="hidden" name="id" value="${employee.ID}">
                </th>
                <c:choose>
                    <c:when test="${flag.equals(''.concat(employee.ID))}">
                        <td>
                            <input type="text" class="form-control" name="new login" maxlength="512"
                                   placeholder="Enter login"
                                   value="${login}" required>
                            <div style="color: red">
                                Employee login should be unique!
                            </div>
                        </td>
                        <td>
                            <input type="email" class="form-control" name="new email" maxlength="512"
                                   aria-describedby="emailHelp"
                                   placeholder="Enter email"
                                   value=" ${email}" required>
                            <div style="color: red">
                                Employee email should be unique!
                            </div>
                        </td>
                        <td>
                            <input type="number" min="1" max="1000" class="form-control" placeholder="Enter rank"
                                   name="new rank"
                                   value="${rank}" required>
                        </td>
                        <td>
                            <input type="date" class="form-control" min="1979-12-31" max="2079-12-31" name="new date"
                                   value="${date}" required>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <input type="text" class="form-control" name="new login" maxlength="512"
                                   placeholder="Enter login"
                                   value="${employee.login}" required>
                        </td>
                        <td>
                            <input type="email" class="form-control" name="new email" maxlength="512"
                                   aria-describedby="emailHelp"
                                   placeholder="Enter email"
                                   value=" ${employee.email}" required>
                        </td>
                        <td>
                            <input type="number" min="1" max="1000" class="form-control" placeholder="Enter rank"
                                   name="new rank"
                                   value="${employee.rank}" required>
                        </td>
                        <td>
                            <input type="date" class="form-control" min="1979-12-31" max="2079-12-31" name="new date"
                                   value="${employee.dateString}" required>
                        </td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <button type="button" class="btn btn-warning" onclick="doEdit(${employee.ID})">Edit</button>
                </td>
                <td>
                    <button type="button" onclick="doDelete(${employee.ID})" class="btn btn-danger">Delete</button>
                </td>
            </form>
        </tr>
    </c:forEach>
    <tr>
        <form id="add" method="post">
            <input type="hidden" name="action-post" value="add new employee">
            <th>*</th>
            <c:choose>
                <c:when test="${flag.equals('invalid-new-employee')}">
                    <td>
                        <input type="text" class="form-control" name="new login" maxlength="512"
                               placeholder="Enter login"
                               value="${login}" required>
                        <div style="color: red">
                            New employee login should be unique!
                        </div>
                    </td>
                    <td>
                        <input type="email" class="form-control" name="new email" maxlength="512"
                               aria-describedby="emailHelp"
                               placeholder="Enter email" value="${email}" required>
                        <div style="color: red">
                            New employee email should be unique!
                        </div>
                    </td>
                    <td>
                        <input type="number" name="new rank" min="1" max="1000" class="form-control"
                               placeholder="Enter rank"
                               value="${rank}" required>
                    </td>
                    <td>
                        <input type="date" class="form-control" min="1979-12-31" max="2079-12-31" name="new date"
                               value="${date}" required>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <input type="text" class="form-control" name="new login" maxlength="512"
                               placeholder="Enter login" required>
                    </td>
                    <td>
                        <input type="email" class="form-control" name="new email" maxlength="512"
                               aria-describedby="emailHelp"
                               placeholder="Enter email" required>
                    </td>
                    <td>
                        <input type="number" name="new rank" min="1" max="1000" class="form-control"
                               placeholder="Enter rank"
                               required>
                    </td>
                    <td>
                        <input type="date" class="form-control" min="1979-12-31" max="2079-12-31" name="new date"
                               required>
                    </td>
                </c:otherwise>
            </c:choose>
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

    function doEdit(id) {
        document.getElementById('action ' + id).value = "employee edit";
        document.getElementById('post ' + id).submit();
    }
</script>
</body>
<style>

</style>
</html>
