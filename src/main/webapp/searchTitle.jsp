<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Page</title>
    <%@ include file="includes/header.jsp"%>

</head>
<body>
<div class="container">
    <div clas="hero-unit">
        <h2>Search Movies List By Title</h2>
    </div>

    <%@ include file="includes/navigation.jsp"%>

    <div clas="container">

        <form action="SearchTitle" method="get">

            <label>Search by Title:</label>
            <input type="text" name="title" id="title"/>
            <br>
            <input type="submit">

        </form>
    </div>
</div>
</body>
</html>
