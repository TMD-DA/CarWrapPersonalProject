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
        <title>Wrap - Submit Review</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="wrap.css" rel="stylesheet" type="text/css">
    </head>
    <body class="bg-light">
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <br>
            <h1>Submit a review!</h1>
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
                            <input type="hidden" name="action" value="submitReview">
                            <label for="rating-field">Rating</label>
                            <br>
                            <select id="rating-field" name="rating" class="form-control">
                                <option value="" disabled selected>Select a rating number.</option>
                                <% for (int i = 1; i <= 10; i++) {%>
                                <option value="<%= i%>"><%= i%></option>
                                <% }%>
                            </select>
                            <br>
                            <label for="review-field">Review</label>
                            <br>
                            <textarea id="review-field" name="review" maxlength="500" rows="5" cols="50" class="form-control"></textarea>
                            <p>Character Limit: 500</p>
                            <br>
                            <button type="submit" class="btn btn-success">Submit Review</button>
                        </form>
                    </div>
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
    </body>
</html>
