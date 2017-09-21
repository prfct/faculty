<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <form method="post" action="/app/user/update">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4><fmt:message key="update_user.header.text" bundle="${general}"/></h4>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="id"> <fmt:message key="id.text" bundle="${general}"/></label>
                    <input class="form-control" id="id" type="text" name="id" value="${user.id}" readonly/>
                </div>
                <div class="form-group">
                    <label for="username">
                        <fmt:message key="username.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="username" type="text" name="username"
                           value="<c:out value="${user.username}"/>"/>
                    <c:if test="${not empty username_error}">
                        <span class="error">
                            <fmt:message key="${username_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="birthdayInput">
                        <fmt:message key="birthday.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="birthdayInput" type="text" name="birthday"
                           value="${user.birthDate}" readonly/>
                </div>
                <div class="form-group">
                    <label for="userRole">
                        <fmt:message key="role.text" bundle="${general}"/>
                    </label>
                    <select class="form-control" id="userRole" name="userRole">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role}">${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <input type="submit" value="Update" class="btn btn-primary"/>
            </div>
        </div>
    </form>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>
