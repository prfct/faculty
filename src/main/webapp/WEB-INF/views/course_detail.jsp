<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4>
                <fmt:message key="course_detail.header.text" bundle="${general}"/>
            </h4>
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label for="title">
                    <fmt:message key="course_title.text" bundle="${general}"/>
                </label>
                <input class="form-control" id="title" type="text" name="title" readonly
                       value="<c:out value="${course.title}"/>"
                />
            </div>
            <div class="form-group">
                <label for="date">
                    <fmt:message key="course_date.text" bundle="${general}"/>
                </label>
                <input class="form-control" id="date" type="text" name="createDate" readonly
                       value="<c:out value="${course.createDate}"/>"
                />
            </div>
            <div class="form-group">
                <label for="teacherName">
                    <fmt:message key="current_teacher.text" bundle="${general}"/>
                </label>
                <input class="form-control" id="teacherName" type="text" name="createDate" readonly
                       value="<c:out value="${course.user.username}"/>"
                />
            </div>
            <div class="form-group">
                <label>
                    <fmt:message key="current_students.text" bundle="${general}"/>
                </label>
                <c:forEach items="${course.students}" var="student">
                    <br>
                    ${student.user.username}
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>


<%--<div class="form-group">--%>
<%--<label for="studentIds">--%>
<%--<fmt:message key="student.text" bundle="${general}"/>--%>
<%--</label>--%>
<%--<select multiple class="form-control" id="studentIds" name="studentIds">--%>
<%--<c:forEach items="${users}" var="user">--%>
<%--<c:if test="${user.auth.userRole == 'STUDENT'}">--%>
<%--<option value="${user.id}">${user.username}</option>--%>
<%--</c:if>--%>
<%--</c:forEach>--%>
<%--</select>--%>
<%--<c:if test="${not empty studentIds_error}">--%>
<%--<div class="alert alert-danger" role="alert">--%>
<%--<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>--%>
<%--<span class="sr-only">Error:</span>--%>
<%--${studentIds_error}--%>
<%--</div>--%>
<%--</c:if>--%>
<%--</div>--%>
