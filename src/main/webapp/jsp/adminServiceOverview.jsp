<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="serviceOverview.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>
<body class="site">

<jsp:include page="../fragments/adminHeader.jsp"/>

<main class="site-content">
    <div>
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
                                    <a href="controller?command=get_service_edit_page&serviceId=${autoShowService.serviceId}">
                                        <fmt:message key="serviceOverview.label.edit" bundle="${rb}"/>
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

        <div class="col-3">
            <form method="post" action="controller">

                <!-- hidden input -->
                <input type="hidden" name="command" value="get_service_add_page"/>
                <!-- /hidden input -->

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    <fmt:message key="serviceOverview.button.submit" bundle="${rb}"/>
                </button>
            </form>
        </div>

    </div>
</main>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/pagination.js"/>"></script>
<!-- /scripts -->
</body>
</html>
