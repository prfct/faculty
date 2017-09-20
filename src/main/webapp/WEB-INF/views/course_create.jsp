<%@include file="/WEB-INF/includes/header.jsp" %>

<div class="container">
    <form method="post" action="/app/course/create">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>Create new Course</h4>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="idInput">ID</label>
                    <input class="form-control" id="idInput" type="text" name="id" readonly/>
                </div>
                <div class="form-group">
                    <label for="title">Course title</label>
                    <input class="form-control" id="title" type="text" name="title"
                           value="<c:out value="${course.title}"/>"
                    />
                    <c:if test="${not empty title_error}">
                        <div class="alert alert-danger" role="alert">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class="sr-only">Error:</span>
                                ${title_error}
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="createDate">Course date</label>
                    <input class="form-control" id="createDate" type="text" name="createDate" value="${createDate}"/>
                    <c:if test="${not empty createDate_error}">
                        <div class="alert alert-danger" role="alert">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class="sr-only">Error:</span>
                                ${createDate_error}
                        </div>
                    </c:if>
                </div>
                <input type="submit" value="Save" class="btn btn-primary"/>
            </div>
        </div>
    </form>
</div>

<%@include file="/WEB-INF/includes/footer.jsp" %>