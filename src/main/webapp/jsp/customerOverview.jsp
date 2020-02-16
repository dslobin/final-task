<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>
<%--${language}--%>
<html lang="ru">

<head>
    <title><fmt:message key="customerOverview.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>

<body>

<jsp:include page="../fragments/adminHeader.jsp"/>

<div class="page-wrap">
    <jsp:useBean id="customerMap" class="java.util.HashMap" scope="request"/>
    <c:choose>
        <c:when test="${not empty customerMap}">

            <div class="table-wrapper">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th><fmt:message key="customerOverview.tableHeader.username" bundle="${rb}"/></th>
                        <th><fmt:message key="customerOverview.tableHeader.surname" bundle="${rb}"/></th>
                        <th><fmt:message key="customerOverview.tableHeader.name" bundle="${rb}"/></th>
                        <th><fmt:message key="customerOverview.tableHeader.email" bundle="${rb}"/></th>
                        <th><fmt:message key="customerOverview.tableHeader.phoneNumber" bundle="${rb}"/></th>
                        <th><fmt:message key="customerOverview.tableHeader.action" bundle="${rb}"/></th>
                    </tr>
                    </thead>
                    <tbody id="page">
                    <c:forEach var="customer" items="${customerMap}">
                        <tr class="table-row">
                            <td><c:out value="${customer.key}"/></td>
                            <td><c:out value="${customer.value.surname}"/></td>
                            <td><c:out value="${customer.value.name}"/></td>
                            <td><c:out value="${customer.value.email}"/></td>
                            <td><c:out value="${customer.value.phoneNumber}"/></td>
                            <td>
                                <a href="controller?command=get_customer_edit_page&customerId=${customer.value.customerId}">
                                    <fmt:message key="customerOverview.label.edit" bundle="${rb}"/>
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
            <h2><fmt:message key="customerOverview.text.emptyList" bundle="${rb}"/>!</h2>
        </c:otherwise>
    </c:choose>

    <div class="col-3">
        <form method="post" action="controller">

            <!-- hidden input -->
            <input type="hidden" name="command" value="get_customer_add_page"/>
            <!-- /hidden input -->

            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <fmt:message key="customerOverview.button.submit" bundle="${rb}"/>
            </button>
        </form>
    </div>

</div>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/pagination.js"/>"></script>
<!-- /scripts -->
</body>
</html>