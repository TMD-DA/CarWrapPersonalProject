<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<a href="#main-content" id="stmc">Skip to Main Content</a>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="index.jsp">Wrap</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#movie-genius-navbar" aria-controls="movie-genius-navbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="movie-genius-navbar">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item text-dark">
                    <a href="index.jsp" class="nav-link text-dark">Home</a>
                </li>
                <li class="nav-item text-dark">
                    <a href="about.jsp" class="nav-link text-dark">About Us</a>
                </li>
                <li class="nav-item">
                    <a href="Public?action=gotoReviewPage" class="nav-link text-dark">Reviews</a>
                </li>
                <li class="nav-item">
                    <a href="services.jsp" class="nav-link text-dark">Services</a>
                </li>
                <c:if test="${not empty loggedInUser || loggedInUser != null}">
                    <li class="nav-item">
                        <a href="Private?action=gotoUserPage" class="nav-link text-dark">User Page</a>
                    </li>
                    <li class="nav-item">
                        <a href="wrapEstimate.jsp" class="nav-link text-dark">Get an Estimate</a>
                    </li>
                    <li class="nav-item">
                        <form action="Private" method="post">
                            <input type="hidden" value="logout" name="action" />
                            <button role="link" type="submit" class="nav-link text-dark">Log Out</button>
                        </form>
                    </li>
                    
                </c:if>
                <c:if test="${loggedInUser.userType eq 'admin'}">
                    <!-- Begin Admin Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Admin
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">
                                <a href="http://localhost/phpmyadmin/index.php?route=/database/structure&db=wrapdb" target=?_blank? class="nav-link text-dark">Database</a>
                            </li>
                            <li class="dropdown-item">
                                <a href="Private?action=gotoAdminUserNE" class="nav-link text-dark">Cold Call User List</a>
                            </li>
                            <li class="dropdown-item">
                                <a href="Private?action=adminUserAction" class="nav-link text-dark">Admin Users</a>
                            </li>
                            <li class="dropdown-item">
                                <a href="Private?action=gotoAdminReviews" class="nav-link text-dark">Admin Reviews</a>
                            </li>
                            <li class="dropdown-item">
                                <a href="Private?action=gotoAdminQuotes" class="nav-link text-dark">Admin Quotes</a>
                            </li>
                        </ul>
                    </li>
                    <!-- End Admin Dropdown -->

                </c:if>
                <c:if test="${empty sessionScope.loggedInUser}">
                    <li class="nav-item">
                        <a href="register.jsp" class="nav-link text-dark">Register</a>
                    </li>
                    <li class="nav-item">
                        <a href="login.jsp" class="nav-link text-dark">Log In</a>
                    </li>
                    
                </c:if>
            </ul>
        </div>
    </nav>
</header>