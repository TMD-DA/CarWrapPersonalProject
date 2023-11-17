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
        <title>Wrap - Update User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="wrap.css" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1>Welcome <c:out value="${loggedInUser.username}"/> update your profile information here.</h1>

            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <c:if test="${not empty errorList}">
                            <ul class="list-unstyled">
                                <c:forEach items="${errorList}" var="error">
                                    <li class="text-danger">${error}</li>
                                    </c:forEach>
                            </ul>
                        </c:if>
                        <form action="Private" method="post">
                            <input type="hidden" name="action" value="updateUser">
                            <label for="email-field">Email</label>
                            <br>
                            <input type="email" id="email-field" name="email" class="form-control"/>
                            <br>
                            <label for="username-field">Username</label>
                            <br>
                            <input type="text" id="username-field" name="username" class="form-control"/>
                            <br>
                            <label for="phone-field">Phone Number</label>
                            <br>
                            <input type="text" id="phone-field" name="phone" class="form-control"/>
                            <br>
                            <label for="password-field">Password</label>
                            <br>
                            <input type="password" id="password-field" name="password" class="form-control"/>
                            <br>
                            <label for="verify-password-field">Verify Password</label>
                            <br>
                            <input type="password" id="verify-password-field" name="verify-password" class="form-control"/>
                            <br>
                            <button type="submit" class="btn btn-success">Update Profile</button>
                        </form>
                        <br><br><br>
                        <form action="Private" method="post">
                            <input type="hidden" name="action" value="deleteAccount">
                            <button type="submit" class="btn btn-success">Delete Account</button>
                        </form>
                    </div>
                    <div class="col-4"></div>
                </div>
            </div>
        </main>


    </body>
</html>