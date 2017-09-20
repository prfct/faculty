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
                    <th></th>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${course}" var="course">
                    <tr>
                        <td><a href="/app/course/detail?id=${course.id}">${course.id}</a></td>
                        <td><c:out value="${course.title}"/></td>
                        <td><javatime:format value="${course.createDate}" style="MS"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>
