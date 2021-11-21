<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Movie Page</title>
    <%@ include file="includes/header.jsp"%>

</head>
<body>
<div class="container">
    <div clas="hero-unit">
        <h2></h2>
    </div>

    <%@ include file="includes/navigation.jsp"%>
    <p style="color: ${color}">${message}</p>
    <div clas="container">
        <p>Add a new movie to the movies list.</p>
        <form action="AddMovie" method="post">
            <label for="title">Title:</label>
            <input type="text" name="title" id="title"/>
            <br>
            <label for="director">Director:</label>
            <input type="text" name="director" id="director"/>
            <br>
            <label for="lengthInMinutes">Length In Minutes:</label>
            <input type="text" name="lengthInMinutes" id="lengthInMinutes"/>
            <br>
            <input type="submit">

        </form>
    </div>
</div>
</body>
</html>
