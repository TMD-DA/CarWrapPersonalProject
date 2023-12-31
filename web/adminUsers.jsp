<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (session.getAttribute("loggedInUser") == null) {
        // Redirect to a different page if loggedInUser is not in the session
        response.sendRedirect("login.jsp"); // Replace "login.jsp" with the desired redirection URL
        return; // To stop further execution of JSP
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wrap - Admin All Users</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="wrap.css" rel="stylesheet" type="text/css">
    </head>
    <body class="bg-light">
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <h1>Admin all users page</h1>
                        <br>
                    </div>
                    <table class="table table-striped" border='1' column='1'>
                        <thead>
                            <tr class="table-dark">
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Phone Number</th>
                                <th>Email Address</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="userData" items="${allUsers}">
                                <tr>
                                    <td>${userData.firstName}</td>
                                    <td>${userData.lastName}</td>
                                    <td>${userData.phone}</td>
                                    <td>${userData.email}</td>
                                    <td>
                                        <form action="Private" method="post">
                                            <input type="hidden" name="userID" value="${userData.userID}"/>
                                            <input type="hidden" name="action" value="adminDeleteUser" class="form-control"/>
                                            <button type="submit" class="btn btn-success">Delete User</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </ul>
                    <div class="col-4"></div>
                </div>

            </div>
        </main>
    </body>
</html>
