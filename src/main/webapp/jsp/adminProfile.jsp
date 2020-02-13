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

<jsp:include page="../fragments/adminHeader.jsp"/>

<%-- PROFILE --%>
<div class="container page-wrap">

    <h2>Personal information</h2>
    <div class="row">
        <div class="col-6">
            <h4>Username: ${userLogin}</h4>
            <h4>Role: <tags:role userRole="${userRole}"/></h4>
        </div>
    </div>
</div>
<%-- /PROFILE --%>

<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>