<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="id.text" bundle="${general}"/></th>
            <th><fmt:message key="username.text" bundle="${general}"/></th>
            <th><fmt:message key="birthday.text" bundle="${general}"/></th>
            <th><fmt:message key="role.text" bundle="${general}"/></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.birthDate}</td>
                    <td>${user.auth.userRole}</td>
                    <td>
                        <a href="/app/user/update?id=${user.id}" class="btn btn-success btn-sm">
                            <fmt:message key="user_update.text" bundle="${general}"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${pagesQuantity}">
                    <li><a href="/app/user/list?page=${i}">${i}</a></li>
                </c:forEach>
            </ul>
        </tbody>
    </table>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>
