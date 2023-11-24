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
        <title>Wrap - Edit Review</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="wrap.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1>Edit your review!</h1>
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <c:if test="${not empty errors}">
                            <ul class="list-unstyled">
                                <c:forEach items="${errors}" var="error">
                                    <li class="text-danger">${error}</li>
                                    </c:forEach>
                            </ul>
                        </c:if>
                        <form action="Private" method="post">
                            <input type="hidden" name="action" value="userEditReview">
                            <input type="hidden" name="userID" value="<c:out value='${userID}'/>"/>
                            <input type="hidden" name="reviewID" value="<c:out value='${reviewID}'/>"/>
                            <label for="rating-field">Rating</label>
                            <br>
                            <select id="rating-field" name="rating" class="form-control">
                                <option value="" disabled selected>Select a rating number.</option>
                                <c:forEach var="i" begin="1" end="10">
                                    <option value="${i}" <c:if test="${i == userReview[0].rating}">selected</c:if>>${i}</option>
                                </c:forEach>
                            </select>
                            <br>
                            <label for="review-field">Review</label>
                            <br>
<textarea id="review-field" name="review" maxlength="255" rows="5" cols="50" class="form-control">${userReview[0].review}</textarea>                            <p>Character Limit: 255</p>
                            <br>
                            <button type="submit" class="btn btn-success">Edit Review</button>
                        </form>
                    </div>
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
    </body>
</html>
