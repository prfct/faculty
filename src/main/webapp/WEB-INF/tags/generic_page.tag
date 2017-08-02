<%@tag description="Page template" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title" fragment="true" %>
<html>
<head>
    <title>
        <jsp:invoke fragment="title"/>
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/app/login">Faculty</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/app/faculty/create">Create faculty</a></li>
            <li><a href="/app/faculty/list">Faculty list</a></li>
            <li><a href="/app/user/list">User list</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <jsp:doBody/>
</div>
</body>
</html>