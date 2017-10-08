<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="course_status.text" bundle="${general}"/></th>
            <th><fmt:message key="course_title.text" bundle="${general}"/></th>
            <th><fmt:message key="course_date.text" bundle="${general}"/></th>
            <th><fmt:message key="student_mark.text" bundle="${general}"/></th>
            <th><fmt:message key="student_feedback.text" bundle="${general}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>
                    <c:if test="${student.course.active}">
                        <span style="color: red;" class="glyphicon glyphicon-fire"></span>
                    </c:if>
                </td>
                <td>${student.course.title}</td>
                <td>${student.course.createDate}</td>
                <td>${student.mark}</td>
                <td>${student.feedback}</td>
            </tr>
        </c:forEach>
        <ul class="pagination">
            <c:forEach var="i" begin="1" end="${pagesCount}">
                <li><a href="/app/user/list?page=${i}">${i}</a></li>
            </c:forEach>
        </ul>
        </tbody>
    </table>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>

