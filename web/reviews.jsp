<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wrap - User Review</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="wrap.css" rel="stylesheet" type="text/css">
    </head>
    <body class="bg-light">
        <jsp:include page="/layout/navbar.jsp" />
        <main id="main-content">
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <h1>Customer Reviews</h1>
                    </div>
                    <div class="col-4"></div>
                </div>
            </div>
            <br><br>
            <div class="container">
                <div class="row">
                    <c:forEach var="userData" items="${userReview}">
                        <div class="col-md-4">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h5 class="card-title">${userData.username}</h5>
                                    <p class="card-text">${userData.review}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </main>
    </body>
</html>