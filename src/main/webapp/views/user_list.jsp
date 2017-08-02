a<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic_page>
    <jsp:attribute name="title">User List</jsp:attribute>
    <jsp:body>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role</th>
            </tr>a
            </thead>
            <tbody>

            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.userRole}</td>
                </tr>
            </c:forEach>
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${pagesCount}">
                    <li><a href="/app/user/list?page=${i}">${i}</a></li>
                </c:forEach>
            </ul>
            </tbody>
        </table>
    </jsp:body>
</t:generic_page>

