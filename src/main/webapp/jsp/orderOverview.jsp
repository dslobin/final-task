<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="orderOverview.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>
<body class="site">

<jsp:include page="../fragments/adminHeader.jsp"/>

<main class="site-content">
    <div>
        <jsp:useBean id="orderList" class="java.util.ArrayList" scope="request"/>
        <c:choose>
            <c:when test="${not empty orderList}">

                <div>
                    <table class="table table-bordered table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th><fmt:message key="orderOverview.tableHeader.customer" bundle="${rb}"/></th>
                            <th><fmt:message key="orderOverview.tableHeader.serviceTitle" bundle="${rb}"/></th>
                            <th><fmt:message key="orderOverview.tableHeader.date" bundle="${rb}"/></th>
                            <th><fmt:message key="orderOverview.tableHeader.overallPrice" bundle="${rb}"/></th>
                            <th><fmt:message key="orderOverview.tableHeader.status" bundle="${rb}"/></th>
                            <th><fmt:message key="orderOverview.tableHeader.action" bundle="${rb}"/></th>
                        </tr>
                        </thead>
                        <tbody id="page">
                        <c:forEach var="order" items="${orderList}">
                            <tr class="table-row">
                                <td><c:out value="${order.customer.surname} ${order.customer.name}"/></td>
                                <td><c:out value="${order.service.title}"/></td>
                                <td><c:out value="${order.serviceTime}"/></td>
                                <td><c:out value="${order.price}"/></td>
                                <td><c:out value="${order.status}"/></td>
                                <td>
                                    <c:if test="${order.status == 'NEW'}">
                                        <a href="controller?command=accept_order&orderId=${order.orderId}"
                                           class="text-success mr-2">
                                            <fmt:message key="orderOverview.label.acceptOrder" bundle="${rb}"/>
                                        </a>

                                        <a href="controller?command=reject_order&orderId=${order.orderId}"
                                           class="text-danger ml-2">
                                            <fmt:message key="orderOverview.label.rejectOrder" bundle="${rb}"/>
                                        </a>
                                    </c:if>
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
                <h2><fmt:message key="orderOverview.label.emptyList" bundle="${rb}"/></h2>
            </c:otherwise>
        </c:choose>

        <div class="col-3">
            <form method="post" action="controller">

                <!-- hidden input -->
                <input type="hidden" name="command" value="get_order_add_page"/>
                <!-- /hidden input -->

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    <fmt:message key="orderOverview.button.submit" bundle="${rb}"/>
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