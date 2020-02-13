<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>

<html lang="ru">
<head>
    <title><fmt:message key="serviceOverview.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>
<body>

<jsp:include page="../fragments/clientHeader.jsp"/>

<div class="page-wrap">
    <jsp:useBean id="autoShowServiceList" class="java.util.ArrayList" scope="request"/>
    <c:choose>
        <c:when test="${not empty autoShowServiceList}">

            <div class="table-wrapper">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th><fmt:message key="serviceOverview.tableHeader.serviceTitle" bundle="${rb}"/></th>
                        <th><fmt:message key="serviceOverview.tableHeader.cost" bundle="${rb}"/></th>
                        <th><fmt:message key="serviceOverview.tableHeader.serviceDescription" bundle="${rb}"/></th>
                        <th><fmt:message key="serviceOverview.tableHeader.action" bundle="${rb}"/></th>
                    </tr>
                    </thead>
                    <tbody id="page">
                    <c:forEach var="autoShowService" items="${autoShowServiceList}">
                        <tr class="table-row">
                            <td><c:out value="${autoShowService.title}"/></td>
                            <td><c:out value="${autoShowService.cost}"/></td>
                            <td><c:out value="${autoShowService.description}"/></td>
                            <td>
                                <a href="controller?command=edit_service_command&serviceId=${autoShowService.serviceId}">
                                    <fmt:message key="serviceOverview.label.signUp" bundle="${rb}"/>
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
            <h2><fmt:message key="serviceOverview.text.emptyList" bundle="${rb}"/></h2>
        </c:otherwise>
    </c:choose>

</div>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/pagination.js"/>"></script>
<!-- /scripts -->
</body>
</html>
