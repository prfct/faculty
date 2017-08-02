<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:choose>
    <c:when test="${showRegisterForm != null && showRegisterForm}">
        <c:set var="loginStyle" value="display: none;"/>
        <c:set var="registrationStyle" value="display: block;"/>
    </c:when>
    <c:otherwise>
        <c:set var="loginStyle" value="display: block;"/>
        <c:set var="registrationStyle" value="display: none;"/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .error {
            color: red;
        }

        body {
            padding-top: 90px;
        }

        .panel-login {
            border-color: #ccc;
            -webkit-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
            -moz-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
            box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
        }

        .panel-login > .panel-heading {
            color: #00415d;
            background-color: #fff;
            border-color: #fff;
            text-align: center;
        }

        .panel-login > .panel-heading a {
            text-decoration: none;
            color: #666;
            font-weight: bold;
            font-size: 15px;
            -webkit-transition: all 0.1s linear;
            -moz-transition: all 0.1s linear;
            transition: all 0.1s linear;
        }

        .panel-login > .panel-heading a.active {
            color: #029f5b;
            font-size: 18px;
        }

        .panel-login > .panel-heading hr {
            margin-top: 10px;
            margin-bottom: 0px;
            clear: both;
            border: 0;
            height: 1px;
            background-image: -webkit-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
            background-image: -moz-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
            background-image: -ms-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
            background-image: -o-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
        }

        .panel-login input[type="text"], .panel-login input[type="email"], .panel-login input[type="password"] {
            height: 45px;
            border: 1px solid #ddd;
            font-size: 16px;
            -webkit-transition: all 0.1s linear;
            -moz-transition: all 0.1s linear;
            transition: all 0.1s linear;
        }

        .panel-login input:hover,
        .panel-login input:focus {
            outline: none;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            border-color: #ccc;
        }

        .btn-login {
            background-color: #59B2E0;
            outline: none;
            color: #fff;
            font-size: 14px;
            height: auto;
            font-weight: normal;
            padding: 14px 0;
            text-transform: uppercase;
            border-color: #59B2E6;
        }

        .btn-login:hover,
        .btn-login:focus {
            color: #fff;
            background-color: #53A3CD;
            border-color: #53A3CD;
        }

        .forgot-password {
            text-decoration: underline;
            color: #888;
        }

        .forgot-password:hover,
        .forgot-password:focus {
            text-decoration: underline;
            color: #666;
        }

        .btn-register {
            background-color: #1CB94E;
            outline: none;
            color: #fff;
            font-size: 14px;
            height: auto;
            font-weight: normal;
            padding: 14px 0;
            text-transform: uppercase;
            border-color: #1CB94A;
        }

        .btn-register:hover,
        .btn-register:focus {
            color: #fff;
            background-color: #1CA347;
            border-color: #1CA347;
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
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="javascript:void(0)" class="active" id="login-form-link">${localeBundle.getString('login.header.text')}</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="javascript:void(0)" id="register-form-link">${localeBundle.getString('registration.header.text')}</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="/app/login" method="post"
                                  userRole="form" style="${loginStyle}">
                                <div class="form-group">
                                    <input type="text" name="email" id="login_email" tabindex="1"
                                           class="form-control" placeholder="example@mail.ru">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="login_password" tabindex="2"
                                           class="form-control" placeholder="Password"/>
                                </div>
                                <div class="form-group">
                                    <c:if test="${login_error != null}">
                                        <div class="form-group">
                                            <label class="error">${login_error}</label>
                                        </div>
                                    </c:if>
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit"
                                                   tabindex="4" class="form-control btn btn-login"
                                                   value="Log In"/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form id="register-form" action="/app/registration" method="post"
                                  userRole="form" style="${registrationStyle}">
                                <div class="form-group">
                                    <input type="text" name="username" id="register_username" tabindex="1"
                                           class="form-control" placeholder="Username">
                                    <c:if test="${not empty username_error}">
                                        <div class="alert alert-danger" userRole="alert">
                                            <span class="glyphicon glyphicon-exclamation-sign"
                                                    aria-hidden="true"></span>
                                            <span class="sr-only">Error:</span>
                                                ${username_error}
                                        </div>
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="email" id="register_email" tabindex="1"
                                           class="form-control" placeholder="Email Address" value="">
                                    <c:if test="${not empty email_error}">
                                        <div class="alert alert-danger" userRole="alert">
                                            <span class="glyphicon glyphicon-exclamation-sign"
                                                    aria-hidden="true"></span>
                                            <span class="sr-only">Error:</span>
                                                ${email_error}
                                        </div>
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="register_password" tabindex="2"
                                    <c:out value="${user.password}"/>
                                           class="form-control" placeholder="Password">
                                    <c:if test="${not empty password_error}">
                                        <div class="alert alert-danger" userRole="alert">
                                        <span class="glyphicon glyphicon-exclamation-sign"
                                              aria-hidden="true"></span>
                                            <span class="sr-only">Error:</span>
                                                ${password_error}
                                        </div>
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <c:if test="${registration_error != null}">
                                        <div class="form-group">
                                            <label class="error">${registration_error}</label>
                                        </div>
                                    </c:if>
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit"
                                                   tabindex="4" class="form-control btn btn-register"
                                                   value="Register Now">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>