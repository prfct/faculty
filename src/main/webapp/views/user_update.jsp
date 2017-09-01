<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic_page>
    <jsp:attribute name="title">Update user</jsp:attribute>
    <jsp:body>
        <form method="post" action="/app/student/update">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>Update user</h4>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="id">ID</label>
                        <input class="form-control" id="id" type="text" name="id" value="${user.id}" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input class="form-control" id="username" type="text" name="username"
                               value="<c:out value="${user.username}"/>"/>
                        <c:if test="${not empty username_error}">
                            <div class="alert alert-danger" role="alert">
                                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                <span class="sr-only">Error:</span>
                                    ${username_error}
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="email">User email</label>
                        <input class="form-control" id="email" type="text" name="email"
                               value="<c:out value="${user.email}"/>"/>
                        <c:if test="${not empty email_error}">
                            <div class="alert alert-danger" role="alert">
                                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                <span class="sr-only">Error:</span>
                                    ${email_error}
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="password">User password</label>
                        <input class="form-control" id="password" type="text" name="password" value="${user.password}"/>
                        <c:if test="${not empty password_error}">
                            <div class="alert alert-danger" role="alert">
                                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                <span class="sr-only">Error:</span>
                                    ${password_error}
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="userRole">Roles</label>
                        <select multiple class="form-control" id="userRole" name="userRoles">
                            <c:forEach items="${roles}" var="role">
                                <option value="${role}">${role.name()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" value="Update" class="btn btn-primary"/>
                </div>
            </div>
        </form>
    </jsp:body>
</t:generic_page>

