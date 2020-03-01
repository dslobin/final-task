<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="errorPage.head.title" bundle="${rb}"/></title>
    <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/style.css" />" rel="stylesheet"/>
</head>
<body>

<section class="bg-image fullscreen">
    <div class="overlay"></div>

    <div class="container align-left text-white">
        <h2 class="py-3 display-1 title"><fmt:message key="errorPage.label.header" bundle="${rb}"/></h2>
        <h2 class="py-3 display-4"><fmt:message key="errorPage.label.main" bundle="${rb}"/></h2>
        <h4 class="py-3 display-5"><c:out value="${requestScope.wrongAction}"/></h4>
        <h4 class="py-3 display-5"><c:out value="${requestScope.forbidden}"/></h4>
        <h4 class="py-3 display-5"><c:out value="${requestScope.serverError}"/></h4>
            <div class="py-4 center-align">
                <a class="btn btn-lg back-button" href="controller?command=get_home_page">
                    <fmt:message key="errorPage.link.home" bundle="${rb}"/></a>
            </div>
        </h2>
    </div>
</section>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<!-- /scripts -->
</body>
</html>