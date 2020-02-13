<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" uri="/WEB-INF/tld/userRole.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>
<%--${language}--%>

<html>

<head>
    <title>Profile</title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>

<body>

<jsp:useBean id="userRole" scope="session" type="by.epam.autoshow.model.UserRole"/>
<jsp:useBean id="userLogin" scope="session" type="java.lang.String"/>


<jsp:include page="../fragments/clientHeader.jsp"/>


<%-- PROFILE --%>
<div class="container">

    <h2>Personal information</h2>
    <div class="row">
        <div class="col-6">
            <h4>Username: ${userLogin}</h4>
            <h4>Role: <tags:role userRole="${userRole}"/></h4>
        </div>
        <jsp:useBean id="customer" scope="request" type="by.epam.autoshow.model.Customer"/>
        <c:when test="${customer != null}">
            <div class="col-6">
                <h4>
                    Name:
                    <c:out value="${customer.name}"/>
                </h4>
                <h4>
                    Surname:
                    <c:out value="${customer.surname}"/>
                </h4>
            </div>
        </c:when>
    </div>

    <h2>Service order history:</h2>
    <div class="page-wrap">
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
                        </tr>
                        </thead>
                        <tbody id="page">
                        <c:forEach var="order" items="${customerOrders}">
                            <tr class="table-row">
                                <td><c:out value="${order.serviceId}"/></td>
                                <td><c:out value="${order.orderDate}"/></td>
                                <td><c:out value="${order.overallPrice}"/></td>
                                <td><c:out value="${order.status}"/></td>
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
                <h2><c:out value="Order list is empty"/></h2>
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
</body>
</html>