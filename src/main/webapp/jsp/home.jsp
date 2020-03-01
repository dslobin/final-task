<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title>Favorite Motors</title>
    <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value='/static/fontawesome-free/css/all.min.css' />" rel="stylesheet" type="text/css"/>
    <link href="<c:url value="/static/css/home-page.css" />" rel="stylesheet"/>
</head>
<body>

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

<header class="masthead text-white text-center">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-xl-9 mx-auto">
                <h1>Favorite Motors</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-xl-9 mx-auto">
                <h3 class="mb-5"><fmt:message key="home.text.autoShowInfo" bundle="${rb}"/></h3>
            </div>
        </div>
    </div>
</header>

<section class="features-icons bg-light text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                    <div class="features-icons-icon d-flex">
                        <i class="fas fa-car m-auto text-primary"></i>
                    </div>
                    <h3><fmt:message key="home.carInfo.header" bundle="${rb}"/></h3>
                    <p class="lead mb-0"><fmt:message key="home.carInfo.body" bundle="${rb}"/></p>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="features-icons-item mx-auto mb-0 mb-lg-3">
                    <div class="features-icons-icon d-flex">
                        <i class="fas fa-wrench m-auto text-primary"></i>
                    </div>
                    <h3><fmt:message key="home.serviceInfo.header" bundle="${rb}"/></h3>
                    <p class="lead mb-0"><fmt:message key="home.serviceInfo.body" bundle="${rb}"/></p>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="features-icons-item mx-auto mb-0 mb-lg-3">
                    <div class="features-icons-icon d-flex">
                        <i class="fas fa-credit-card m-auto text-primary"></i>
                    </div>
                    <h3><fmt:message key="home.creditInfo.header" bundle="${rb}"/></h3>
                    <p class="lead mb-0"><fmt:message key="home.creditInfo.body" bundle="${rb}"/></p>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<!-- /scripts -->
</body>
</html>