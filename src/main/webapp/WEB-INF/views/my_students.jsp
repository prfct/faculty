<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="course_status.text" bundle="${general}"/></th>
            <th><fmt:message key="course_title.text" bundle="${general}"/></th>
            <th><fmt:message key="course_date.text" bundle="${general}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>
                    <c:if test="${course.active}">
                        <span style="color: red;" class="glyphicon glyphicon-fire"></span>
                    </c:if>
                </td>
                <td><a href="/app/course/detail?id=${course.id}">${course.title}</a></td>
                <td>${course.createDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>

