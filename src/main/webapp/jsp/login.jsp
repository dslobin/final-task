<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="properties.pageContent"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="login.head.title"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
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
    <div class="success-content-wrapper align-items-center">
        <p class="text-success">${requestScope.completedRegistration}</p>
    </div>

    <div class="mt-5 login-form">
        <form class="needs-validation" id="loginForm" action="controller" method="post">

            <!-- hidden input -->
            <input type="hidden" name="command" value="login"/>
            <!-- /hidden input -->

            <h1 class="h3 mb-3 font-weight-normal text-center"><fmt:message key="login.text.header"/></h1>

            <div class="form-group">
                <label for="username"><fmt:message key="login.label.username"/>: </label>
                <input type="text" id="username" name="username" maxlength="30"
                       class="form-control" required autofocus>
            </div>

            <div class="form-group">
                <label for="password"><fmt:message key="login.label.password"/>: </label>
                <input type="password" id="password" name="password"
                       minlength="6" maxlength="30" class="form-control" required>
            </div>

            <div>
                <p class="text-center text-muted small">
                    <fmt:message key="login.text.accountAvailability"/>
                    <a href="controller?command=get_registration_page">
                        <fmt:message key="login.link.registration"/>
                    </a>
                </p>
            </div>

            <%--<div>
                <p class="text-center small">
                    <a href="controller?command=regain_password">
                        <fmt:message key="login.link.passwordRecovery" bundle="${rb}"/>
                    </a>
                </p>
            </div>--%>

            <div class="text-danger">
                <p>${requestScope.incorrectLoginPassword}</p>
            </div>

            <div class="text-warning">
                <p>${requestScope.errorBlockedUser}</p>
            </div>

            <button class="btn btn-lg btn-success btn-block" type="submit">
                <fmt:message key="login.button.submit"/>
            </button>
        </form>
    </div>
</main>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/validation.js"/>"></script>
<!-- /scripts -->
</body>
</html>