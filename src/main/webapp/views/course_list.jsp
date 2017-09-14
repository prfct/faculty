<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages" var="general"/>


<t:generic_page>
    <jsp:attribute name="title">Course list</jsp:attribute>
    <jsp:body>
        <form action="/app/localization" method="post">
            <select id="language" name="language" onchange="submit()" style="position: absolute; right: 2px; top: 2px;">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
            </select>
            <input type="hidden" id="currentUrl" name="currentUrl" value=""/>
        </form>
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
    </jsp:body>
</t:generic_page>
