<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Populate Page</title>
    <%@ include file="includes/header.jsp"%>

</head>
<body>
<div class="container">
    <div clas="hero-unit">
        <h2>Populate the Database</h2>
    </div>

    <%@ include file="includes/navigation.jsp"%>

    <div clas="container">

        <form action="Populate" method="post">
            <p><b>WARNING!</b> Populate the database will over-write all existing information in the database.
                Any existing information will be lost</p>
            <input type="submit" value="Populate DB">
            <br>
            <p style="color: ${color}">${message}</p>
        </form>

    </div>
</div>
</body>
</html>