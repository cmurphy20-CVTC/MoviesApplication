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
        <h2>Search Movies List By Director</h2>
    </div>

    <%@ include file="includes/navigation.jsp"%>

    <div clas="container">

        <form action="SearchDirector" method="get">

            <label>Search by Director:</label>
            <input type="text" name="director" id="director"/>
            <br>
            <input type="submit">

        </form>
    </div>
</div>
</body>
</html>
