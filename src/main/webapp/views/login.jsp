<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" var="general"/>

<html lang="${language}">
<head>
    <title>Login page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <style>
        .error{
            color: red;
        }
    </style>

    <script type="application/javascript">
        $(function () {
            $('#login-form-link').click(function (e) {
                $("#login-form").delay(100).fadeIn(100);
                $("#register-form").fadeOut(100);
                $('#register-form-link').removeClass('active');
                $(this).addClass('active');
                e.preventDefault();
            });
            $('#register-form-link').click(function (e) {
                $("#register-form").delay(100).fadeIn(100);
                $("#login-form").fadeOut(100);
                $('#login-form-link').removeClass('active');
                $(this).addClass('active');
                e.preventDefault();
            });
        });
        $(document).ready(function () {
            $("#currentUrl").val(window.location.href);
        });
    </script>
</head>
<body>
<form action="/app/localization" method="post">
    <select id="language" name="language" onchange="submit()" style="position: absolute; right: 2px; top: 2px;">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
    </select>
    <input type="hidden" id="currentUrl" name="currentUrl" value=""/>
</form>
<div class="container">
    <form method="post" action="/app/login">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4><fmt:message key="login.header.text" bundle="${general}"/></h4>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="emailInput">email</label>
                    <input class="form-control" id="emailInput" type="text" name="email"/>
                </div>
                <div class="form-group">
                    <label for="passwordInput">Password</label>
                    <input class="form-control" id="passwordInput" type="password" name="password"/>
                </div>
                <div class="form-group">
                    <c:if test="${login_error != null}">
                        <div class="form-group">
                            <label class="error">
                                <fmt:message key="${login_error}" bundle="${general}"/>
                            </label>
                        </div>
                    </c:if>
                </div>
                <input type="submit" value="Submit" class="btn btn-primary"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>
