<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="editUser.head.title" bundle="${rb}"/></title>
    <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/style.css" />" rel="stylesheet"/>
</head>
<body class="site">

<c:choose>
    <c:when test="${sessionScope.userRole == 'ADMIN'}">
        <jsp:include page="../fragments/adminHeader.jsp"/>
    </c:when>

    <c:when test="${sessionScope.userRole == 'CLIENT'}">
        <jsp:include page="../fragments/clientHeader.jsp"/>
    </c:when>

    <c:otherwise>
        <jsp:include page="../fragments/guestHeader.jsp"/>
    </c:otherwise>
</c:choose>

<main class="site-content">
    <div class="position-relative overflow-hidden p-3 p-md-5 text-center bg-light page-top">
        <div class="col-md-5 p-lg-5 mx-auto my-5">
            <h2 class="display-4 font-weight-normal">
                <fmt:message key="aboutCompany.text.contacts" bundle="${rb}"/>
            </h2>
        </div>
    </div>

    <div class="container mt-5">
        <h4><fmt:message key="aboutCompany.text.phoneNumber" bundle="${rb}"/></h4>
        <div class="row">
            <div class="col-4">
                <strong><fmt:message key="aboutCompany.text.salesDepartment" bundle="${rb}"/>:</strong>
                <p><a href="#">+375 29 <strong>111-11-11</strong></a> Velcom</p>
                <p><a href="#">+375 33 <strong>222-22-22</strong></a> MTC</p>
                <p><a href="#">+375 17 <strong>333-33-33</strong></a>
                    <fmt:message key="aboutCompany.text.telephoneOperator" bundle="${rb}"/>
                </p>
            </div>

            <div class="col-4">
                <strong><fmt:message key="aboutCompany.text.commission" bundle="${rb}"/>:</strong>
                <p><a href="#">+375 29 <strong>1234-567</strong></a> Velcom</p>
                <p><a href="#">+375 33 <strong>7654-321</strong></a> MTC</p>
            </div>

            <div class="col-4">
                <strong><fmt:message key="aboutCompany.text.exchange" bundle="${rb}"/>:</strong>
                <p><a href="#">+375 29 <strong>456-78-90</strong></a> Velcom</p>
                <p><a href="#">+375 33 <strong>111-22-33</strong></a> MTC</p>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <h4>E-mail:</h4>
                <p>info@favourite-motors.by</p>
            </div>
        </div>

        <div class="row">
            <div class="col-4">
                <h4><fmt:message key="aboutCompany.text.workingHours" bundle="${rb}"/>:</h4>
                <p><fmt:message key="aboutCompany.text.monday" bundle="${rb}"/>: <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.tuesday" bundle="${rb}"/>: <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.wednesday" bundle="${rb}"/>: <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.thursday" bundle="${rb}"/>: <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.friday" bundle="${rb}"/>: <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.saturday" bundle="${rb}"/>: <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.sunday" bundle="${rb}"/>: <span>
                    <fmt:message key="aboutCompany.text.dayOff" bundle="${rb}"/></span>
                </p>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../fragments/footer.jsp"/>

<%-- scripts --%>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<%-- /scripts --%>
</body>
</html>