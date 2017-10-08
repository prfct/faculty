<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://faculty.ua/tags" prefix="cm" %>
<c:set var = "ADMIN" value="${auth.userRole == 'ADMIN'}"/>
<c:set var = "TEACHER" value="${auth.userRole == 'TEACHER'}"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" var="general"/>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="application/javascript">
        $(document).ready(function () {
            $("#currentUrl").val(window.location.href);
        });
    </script>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/app/login"><fmt:message key="faculty.text" bundle="${general}"/></a>
        </div>
        <ul class="nav navbar-nav">
            <li>
                <c:if test="${ADMIN}">
                    <a href="/app/course/create"><fmt:message key="faculty.create.text" bundle="${general}"/></a>
                </c:if>
            </li>
            <li>
                <a href="/app/course/list"><fmt:message key="faculty.list.text" bundle="${general}"/></a>
            </li>
            <li>
                <c:if test="${ADMIN}">
                    <a href="/app/user/list"><fmt:message key="user.list.text" bundle="${general}"/></a>
                </c:if>
            </li>
            <li>
                <a href="/app/user/courses"><fmt:message key="user.courses.text" bundle="${general}"/></a>
            </li>
            <li>
                <c:if test="${TEACHER}">
                    <a href="/app/teacher/students"><fmt:message key="teacher.students.text" bundle="${general}"/></a>
                </c:if>
            </li>
        </ul>

        <div style="position: absolute; right: 2px; top: 14px;">
            <form action="/app/localization" method="post" style="float: left;">
                <select id="language" name="language" onchange="submit()">
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
                </select>
                <input type="hidden" id="currentUrl" name="currentUrl" value=""/>
            </form>
            <c:choose>
                <c:when test="${auth != null}">
                    <div style="float: left; margin-left: 10px; color: #9d9d9d;">
                        <fmt:message key="welcome.text" bundle="${general}"/>
                        <c:out value="${auth.user.username}"/>
                        (<a href="/app/logout">logout</a>)
                    </div>
                </c:when>
                <c:otherwise>
                    <div style="float: left; margin-left: 10px; color: #9d9d9d;">
                        <fmt:message key="welcome.anonymous.text" bundle="${general}"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>


