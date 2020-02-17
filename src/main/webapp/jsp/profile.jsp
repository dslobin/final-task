<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" uri="/WEB-INF/tld/userRole.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>
<%--${language}--%>

<html lang="ru">
<head>
    <title><fmt:message key="profile.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>
<body>

<jsp:useBean id="userRole" scope="session" type="by.epam.autoshow.model.UserRole"/>
<jsp:useBean id="userLogin" scope="session" type="java.lang.String"/>

<jsp:include page="../fragments/clientHeader.jsp"/>

<%-- PROFILE --%>
<div class="container">
    <h2><fmt:message key="profile.label.personalInformation" bundle="${rb}"/>:</h2>
    <div class="row">
        <c:if test="${customer != null}">
            <div class="col-6">
                <h4><fmt:message key="profile.label.name" bundle="${rb}"/>:
                    <c:out value="${customer.name}"/>
                </h4>
                <h4><fmt:message key="profile.label.surname" bundle="${rb}"/>:
                    <c:out value="${customer.surname}"/>
                </h4>
                <h4><fmt:message key="profile.label.email" bundle="${rb}"/>:
                    <c:out value="${customer.email}"/>
                </h4>
                <h4><fmt:message key="profile.label.phoneNumber" bundle="${rb}"/>:
                    <c:out value="${customer.phoneNumber}"/>
                </h4>
            </div>
        </c:if>

        <div class="col-6">
            <h4><fmt:message key="profile.label.username" bundle="${rb}"/>:
                <c:out value="${userLogin}"/>
            </h4>
            <h4><fmt:message key="profile.label.role" bundle="${rb}"/>:
                <tags:role userRole="${userRole}"/>
            </h4>
        </div>
    </div>

    <h2 class="mt-3"><fmt:message key="profile.label.orderHistory" bundle="${rb}"/>:</h2>
    <div class="">
        <jsp:useBean id="customerOrders" class="java.util.ArrayList" scope="request"/>
        <c:choose>
            <c:when test="${not empty customerOrders}">

                <div class="table-wrapper">
                    <table class="table table-bordered table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th><fmt:message key="orderOverview.tableHeader.serviceId" bundle="${rb}"/></th>
                            <th><fmt:message key="orderOverview.tableHeader.date" bundle="${rb}"/></th>
                            <th><fmt:message key="orderOverview.tableHeader.overallPrice" bundle="${rb}"/></th>
                            <th><fmt:message key="orderOverview.tableHeader.status" bundle="${rb}"/></th>
                            <th><fmt:message key="orderOverview.tableHeader.action" bundle="${rb}"/></th>
                        </tr>
                        </thead>
                        <tbody id="page">
                        <c:forEach var="order" items="${customerOrders}">
                            <tr class="table-row">
                                <td><c:out value="${order.serviceId}"/></td>
                                <td><c:out value="${order.orderDate}"/></td>
                                <td><c:out value="${order.overallPrice}"/></td>
                                <td><c:out value="${order.status}"/></td>
                                <td>
                                    <c:if test="${order.status == 'NEW'}">
                                        <a href="controller?command=cancel_order&orderId=${order.orderId}">
                                            <fmt:message key="profile.label.cancelOrder" bundle="${rb}"/></a>
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
                <h2><fmt:message key="profile.label.emptyHistory" bundle="${rb}"/></h2>
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
</div>
<%-- /PROFILE --%>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/pagination.js"/>"></script>
<!-- /scripts -->
</body>
</html>