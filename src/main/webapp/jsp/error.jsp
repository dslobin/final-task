<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>

<%--${language}--%>
<html lang="ru">
<head>
    <title><fmt:message key="errorPage.head.title" bundle="${rb}"/></title>
    <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/style.css" />" rel="stylesheet"/>
</head>
<body>
<div class="page-wrap d-flex flex-row align-items-center error-content-wrapper">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 text-center">
                <span class="display-1 d-block">
                <fmt:message key="errorPage.label.header" bundle="${rb}"/>!
                </span>
                <div class="mb-4 lead">
                    <fmt:message key="errorPage.label.main" bundle="${rb}"/>
                </div>
                <div class="mb-4">${wrongAction}</div>
                <a href="controller?command=get_home_page" class="btn btn-link">
                    <fmt:message key="errorPage.link.home" bundle="${rb}"/>
                </a>
            </div>
        </div>
    </div>
</div>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<!-- /scripts -->
</body>
</html>
