<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" uri="/WEB-INF/tld/userRole.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="profile.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/fontawesome-free/css/all.min.css' />" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/static/css/profile-page.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>
<body class="site">

<jsp:useBean id="userRole" scope="session" type="by.epam.autoshow.model.UserRole"/>
<jsp:useBean id="userLogin" scope="session" type="java.lang.String"/>

<jsp:include page="../fragments/clientHeader.jsp"/>

<main class="site-content">
    <div class="container">
        <div class="row profile">
            <div class="col-md-3">
                <div class="profile-sidebar">
                    <!-- SIDEBAR USER TITLE -->
                    <c:if test="${customer != null}">
                        <div class="profile-user-title">
                            <div class="profile-user-title-name">
                                <c:out value="${customer.name} ${customer.surname}"/>
                            </div>
                            <div class="profile-user-title-name">
                                <c:out value="@${sessionScope.userLogin}"/>
                            </div>
                            <div class="profile-user-title-role">
                                <tags:role userRole="${sessionScope.userRole}"/>
                            </div>
                        </div>
                        <!-- END SIDEBAR USER TITLE -->
                        <!-- SIDEBAR MENU -->
                        <div class="profile-user-menu">
                            <ul class="nav">
                                <li><a href="#"><i class="fas fa-address-card"></i>
                                    <c:out value="${customer.email}"/></a>
                                </li>
                                <li><a href="#"><i class="fas fa-phone"></i>
                                    <c:out value="${customer.phoneNumber}"/> </a>
                                </li>
                            </ul>
                        </div>
                    </c:if>
                    <!-- END MENU -->
                </div>
            </div>
            <div class="col-md-9">
                <div class="profile-content">
                    <h2 class="mt-3"><fmt:message key="profile.label.orderHistory" bundle="${rb}"/>:</h2>
                    <div>
                        <jsp:useBean id="customerOrders" class="java.util.ArrayList" scope="request"/>
                        <c:choose>
                            <c:when test="${not empty customerOrders}">
                                <div>
                                    <table class="table table-bordered table-striped">
                                        <thead class="thead-dark">
                                        <tr>
                                            <th><fmt:message key="orderOverview.tableHeader.serviceTitle"
                                                             bundle="${rb}"/></th>
                                            <th><fmt:message key="orderOverview.tableHeader.date"
                                                             bundle="${rb}"/></th>
                                            <th><fmt:message key="orderOverview.tableHeader.overallPrice"
                                                             bundle="${rb}"/></th>
                                            <th><fmt:message key="orderOverview.tableHeader.status"
                                                             bundle="${rb}"/></th>
                                            <th><fmt:message key="orderOverview.tableHeader.action"
                                                             bundle="${rb}"/></th>
                                        </tr>
                                        </thead>
                                        <tbody id="page">
                                        <c:forEach var="order" items="${customerOrders}">
                                            <tr class="table-row">
                                                <td><c:out value="${order.service.title}"/></td>
                                                <td><c:out value="${order.serviceTime}"/></td>
                                                <td><c:out value="${order.price}"/></td>
                                                <td><c:out value="${order.status}"/></td>
                                                <td>
                                                    <c:if test="${order.status == 'NEW'}">
                                                        <a href="controller?command=cancel_order&orderId=${order.orderId}">
                                                            <fmt:message key="profile.label.cancelOrder"
                                                                         bundle="${rb}"/></a>
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
                                <h2><fmt:message key="profile.label.emptyHistory" bundle="${rb}"/>.</h2>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/pagination.js"/>"></script>
<!-- /scripts -->
</body>
</html>