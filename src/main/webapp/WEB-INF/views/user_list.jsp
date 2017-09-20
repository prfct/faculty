<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Birthdate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td><javatime:format value="${user.birthDate}" style="M-"/></td>
                <%--<td><javatime:parseLocalDate value="${user.birthDate}" style="MS" /></td>--%>
                <td>
                    <a href="/app/user/update?id=${user.id}"
                       class="btn btn-success btn-sm">update</a>
                </td>
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
