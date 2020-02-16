<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>
<%--${language}--%>
<html>
<head>
    <title><fmt:message key="carOverview.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>

<body>

<jsp:include page="../fragments/clientHeader.jsp"/>

<div class="page-wrap">
    <jsp:useBean id="carList" class="java.util.ArrayList" scope="request"/>
    <c:choose>
        <c:when test="${not empty carList}">

            <div class="">
                <table class="table table-borderless table-striped">
                    <tbody id="page">
                    <c:forEach var="car" items="${carList}">
                        <tr class="table-row">
                            <td width="200px">
                                <img class="card-img-top"
                                     src="${pageContext.request.contextPath}/static/img/default-car-image.png"
                                     alt="Car image" width="200" height="80">
                                    <%--<c:out value="${car.imageUrl}"/>--%>
                            </td>
                            <td>
                                <c:out value="${car.issueYear}"/>
                                <c:out value="${car.price}"/>
                            </td>
                            <td>
                                <c:out value="${car.model}"/>
                                <c:out value="${car.fuelType}"/>
                                <c:out value="${car.mileage}"/>
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
            <h2><fmt:message key="carOverview.text.emptyList" bundle="${rb}"/></h2>
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