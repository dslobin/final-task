<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">

<head>
    <title><fmt:message key="userOverview.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>

<body>

<jsp:include page="../fragments/adminHeader.jsp"/>

<div class="page-wrap">
    <jsp:useBean id="userList" class="java.util.ArrayList" scope="request"/>
    <c:choose>
        <c:when test="${not empty userList}">

            <div class="table-wrapper">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th><fmt:message key="userOverview.tableHeader.username" bundle="${rb}"/></th>
                        <th><fmt:message key="userOverview.tableHeader.password" bundle="${rb}"/></th>
                        <th><fmt:message key="userOverview.tableHeader.role" bundle="${rb}"/></th>
                        <th><fmt:message key="orderOverview.tableHeader.status" bundle="${rb}"/></th>
                        <th><fmt:message key="userOverview.tableHeader.userAction" bundle="${rb}"/></th>
                    </tr>
                    </thead>
                    <tbody id="page">
                    <c:forEach var="user" items="${userList}">
                        <tr class="table-row">
                            <td><c:out value="${user.username}"/></td>
                            <td><c:out value="${user.password}"/></td>
                            <td><c:out value="${user.role}"/></td>
                            <td><c:out value="${user.status}"/></td>
                            <td>
                                <a href="controller?command=get_user_edit_page&userId=${user.userId}">
                                    <fmt:message key="userOverview.label.edit" bundle="${rb}"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="ml-3">
                <jsp:include page="../fragments/pagination.jsp"/>
            </div>

        </c:when>
        <c:otherwise>
            <h2><fmt:message key="userOverview.text.emptyList" bundle="${rb}"/></h2>
        </c:otherwise>
    </c:choose>

    <div class="col-3">
        <form method="post" action="controller">

            <!-- hidden input -->
            <input type="hidden" name="command" value="get_user_add_page"/>
            <!-- /hidden input -->

            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <fmt:message key="userOverview.button.submit" bundle="${rb}"/>
            </button>
        </form>
    </div>

</div>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/pagination.js"/>"></script>
<!-- /scripts -->
</body>
</html>