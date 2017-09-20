<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <form method="post" action="/app/student/update">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>Update user</h4>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="id">ID</label>
                    <input class="form-control" id="id" type="text" name="id" value="${user.id}" readonly/>
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input class="form-control" id="username" type="text" name="username"
                           value="<c:out value="${user.username}"/>"/>
                    <c:if test="${not empty username_error}">
                        <div class="alert alert-danger" role="alert">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class="sr-only">Error:</span>
                                ${username_error}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="birthdayInput">
                        <fmt:message key="birthday.text" bundle="${general}"/>
                    </label>

                    <input class="form-control" id="birthdayInput" type="text" name="birthday"
                            value="<javatime:format value="${user.birthDate}" style="M-"/>" readonly
                    />
                    <c:if test="${not empty birthday_error}">
                        <span class="error">
                            <fmt:message key="${birthday_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="userRole">Roles</label>
                    <select multiple class="form-control" id="userRole" name="userRoles">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role}">${role.name()}</option>
                        </c:forEach>
                    </select>
                </div>
                <input type="submit" value="Update" class="btn btn-primary"/>
            </div>
        </div>
    </form>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>
