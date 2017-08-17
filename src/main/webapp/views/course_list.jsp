<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic_page>
    <jsp:attribute name="title">Course list</jsp:attribute>
    <jsp:body>
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
                    <%--<td><c:out value="${course.date}"/></td>--%>
                    <td><javatime:format value="${course.createDate}" style="MS"/></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:generic_page>
