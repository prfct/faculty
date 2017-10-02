<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <c:choose>
        <c:when test="${courses_error != null}">
            <div class="form-group">
                <div class="form-group">
                    <label class="error">
                        <fmt:message key="${courses_error}" bundle="${general}"/>
                    </label>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="id.text" bundle="${general}"/></th>
                    <th><fmt:message key="course_title.text" bundle="${general}"/></th>
                    <th><fmt:message key="course_date.text" bundle="${general}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td><a href="/app/course/detail?id=${course.id}">${course.id}</a></td>
                        <td><c:out value="${course.title}"/></td>
                        <td>${course.createDate}</td>
                        <td>
                            <a href="/app/course/assign?id=${course.id}" class="btn btn-primary">
                                <fmt:message key="assign.text" bundle="${general}"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>
