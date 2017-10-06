<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <form method="post" action="/app/student/update">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>
                    <fmt:message key="update_student.header.text" bundle="${general}"/>
                </h4>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="studentId">
                        <fmt:message key="id.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="studentId" type="text" name="id"
                           readonly value=${student.id}
                    />
                </div>
                <div class="form-group">
                    <label for="mark">
                        <fmt:message key="student_mark.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="mark" type="text" name="mark" aria-rowcount="5"
                           value="<c:out value="${student.mark}"/>"
                    />
                    <c:if test="${mark_error !=null}">
                        <span class="error">
                            <fmt:message key="${mark_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="feedback">
                        <fmt:message key="student_feedback.text" bundle="${general}"/>
                    </label>
                    <input class="form-control" id="feedback" type="text" name="feedback"
                           value="<c:out value="${student.feedback}"/>"
                    />
                    <c:if test="${feedback_error !=null}">
                        <span class="error">
                            <fmt:message key="${feedback_error}" bundle="${general}"/>
                        </span>
                    </c:if>
                </div>
                <input type="submit" value="<fmt:message key="update_student.header.text" bundle="${general}"/>"
                       class="btn btn-primary"/>
            </div>
        </div>
    </form>
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
