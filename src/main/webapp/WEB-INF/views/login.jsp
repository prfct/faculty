<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <form method="post" action="/app/login">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4><fmt:message key="login.header.text" bundle="${general}"/></h4>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="emailInput">
                        <fmt:message key="email.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="emailInput" type="text" name="email"/>
                </div>
                <div class="form-group">
                    <label for="passwordInput">
                        <fmt:message key="password.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="passwordInput" type="password" name="password"/>
                </div>
                <div class="form-group">
                    <c:if test="${login_error != null}">
                        <span class="error">
                            <fmt:message key="${login_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <input type="submit" value="<fmt:message key="login.header.text" bundle="${general}"/>"
                       class="btn btn-primary"/>
                <a href="/app/registration" class="btn btn-primary">
                    <fmt:message key="registration.header.text" bundle="${general}"/>
                </a>
            </div>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/includes/footer.jsp" %>
