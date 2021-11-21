<%@ page contentType="text/html;charset=UTF-8" language="java" %>+

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/header.jsp"%>
<html>
<head>
    <title>View All</title>
</head>
<body>
<div class="container">
    <div class="hero-unit">
        <h2>View All Movies - Unsorted List</h2>
    </div>

    <%@ include file="includes/navigation.jsp"%>

    <div class="container">

        <c:choose>
            <c:when test="${empty movies}">
                <p>Sorry, the list of movies was empty.</p>
            </c:when>
            <c:otherwise>
                <c:forEach var="movie" items="${movies}">
                    <h2>${movie.title}</h2>
                    <p>${movie.title}, the director is ${movie.director} and the runtime is
                            ${movie.lengthInMinutes} minutes.</p>
                </c:forEach>
            </c:otherwise>
        </c:choose>

    </div>
</div>
</body>
</html>

