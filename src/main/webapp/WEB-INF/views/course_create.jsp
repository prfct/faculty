<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <form method="post" action="/app/course/create">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>
                    <fmt:message key="course_create.header.text" bundle="${general}"/>
                </h4>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="title">
                        <fmt:message key="course_title.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="title" type="text" name="title"
                           value="<c:out value="${course.title}"/>"
                    />
                    <c:if test="${title_error !=null}">
                        <span class="error">
                            <fmt:message key="${title_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="teacherId">
                        <fmt:message key="choose_teacher.text" bundle="${general}"/>
                    </label>
                    <select class="form-control" id="teacherId" name="teacherId">
                        <c:forEach items="${users}" var="user">
                            <c:if test="${user.auth.userRole == 'TEACHER'}">
                                <option value="${user.id}">${user.username}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <input type="submit" value=" <fmt:message key="create.text" bundle="${general}"/>"
                       class="btn btn-primary"/>
            </div>
        </div>
    </form>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>
