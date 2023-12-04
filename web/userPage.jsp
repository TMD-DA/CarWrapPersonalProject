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
        <title>Wrap - User Page</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="wrap.css" rel="stylesheet" type="text/css">
    </head>
    <body class="bg-light">
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <h1>Welcome <c:out value="${loggedInUser.username}"/></h1>
                        <br>
                    </div>
                    <div class="row">
                        <div class="col">
                            <form action="Private" method="post" class="text-end">
                                <input type="hidden" name="action" value="gotoUpdatePage">
                                <button type="submit" class="btn btn-success">Update Profile</button>
                            </form>
                        </div>

                        <c:if test="${not empty userEstimate}">
                            <div class="col">
                                <form action="Private" method="post">
                                    <input type="hidden" name="action" value="gotoReview">
                                    <button type="submit" class="btn btn-success">Submit Review</button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                    
                    <c:if test="${not empty userEstimate}">
                        <table class="table table-striped" border='1' column='1'>
                            <thead>
                                <tr class="table-dark">
                                    <th>Make</th>
                                    <th>Model</th>
                                    <th>Year</th>
                                    <th>Wrap Description</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="userData" items="${userEstimate}">
                                    <tr>
                                        <td>${userData.make}</td>
                                        <td>${userData.model}</td>
                                        <td>${userData.year}</td>
                                        <td>${userData.wrapDescription}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <c:if test="${not empty userReview}">
                        <table class="table table-striped" border='1' column='1'>
                            <thead>
                                <tr class="table-dark">
                                    <th>Review</th>
                                    <th>Rating</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="userData" items="${userReview}">
                                    <tr>
                                        <td>${userData.review}</td>
                                        <td>${userData.rating}</td>
                                        <td>
                                            <form action="Private" method="post">
                                                <input type="hidden" name="userID" value="${userData.userID}"/>
                                                <input type="hidden" name="reviewID" value="${userData.reviewID}"/>
                                                <input type="hidden" name="action" value="gotoReviewEdit" class="form-control"/>
                                                <button type="submit" class="btn btn-success">Edit</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="Private" method="post">
                                                <input type="hidden" name="userID" value="${userData.userID}"/>
                                                <input type="hidden" name="reviewID" value="${userData.reviewID}"/>
                                                <input type="hidden" name="action" value="userDeleteReview" class="form-control"/>
                                                <button type="submit" class="btn btn-success">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
    </body>
</html>
