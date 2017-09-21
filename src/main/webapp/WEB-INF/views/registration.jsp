<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <form method="post" action="/app/registration">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4><fmt:message key="registration.header.text" bundle="${general}"/></h4>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="nameInput">
                        <fmt:message key="username.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="nameInput" type="text" name="username"
                           value="<c:out value="${user.username}"/>"/>
                    <c:if test="${not empty username_error}">
                        <span class="error">
                            <fmt:message key="${username_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="emailInput"><fmt:message key="email.text" bundle="${general}"/></label>
                    <input class="form-control" id="emailInput" type="text" name="email"
                           value="<c:out value="${user.auth.email}"/>"/>
                    <c:if test="${not empty email_error}">
                        <span class="error">
                            <fmt:message key="${email_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="passwordInput">
                        <fmt:message key="password.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="passwordInput" type="password" name="password"/>
                    <c:if test="${not empty password_error}">
                        <span class="error">
                            <fmt:message key="${password_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="birthdayInput">
                        <fmt:message key="birthday.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="birthdayInput" type="text" name="birthday"
                            <c:out value="${user.birthDate}"/>
                    />
                    <c:if test="${not empty birthday_error}">
                        <span class="error">
                            <fmt:message key="${birthday_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <div class="form-group">
                    <c:if test="${registration_error != null}">
                        <div class="form-group">
                            <label class="error">
                                <fmt:message key="${registration_error}" bundle="${general}"/>
                            </label>
                        </div>
                    </c:if>
                </div>
                <input type="submit" value="Register" class="btn btn-primary"/>
            </div>
        </div>
    </form>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>

