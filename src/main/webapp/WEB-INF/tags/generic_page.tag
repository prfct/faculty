<%@tag description="Page template" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<%@attribute name="title" fragment="true" %>

<%--<fmt:setLocale value="${language}"/>--%>
<%--<fmt:setBundle basename="messages" var="general"/>--%>

<html>
<head>
    <title>
        <jsp:invoke fragment="title"/>
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<%--<form action="/app/localization" method="post">--%>
    <%--<select id="language" name="language" onchange="submit()" style="position: absolute; right: 2px; top: 2px;">--%>
        <%--<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>--%>
        <%--<option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>--%>
    <%--</select>--%>
    <%--<input type="hidden" id="currentUrl" name="currentUrl" value=""/>--%>
<%--</form>--%>
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