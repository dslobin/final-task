<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="editUser.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>

<body>

<jsp:include page="../fragments/guestHeader.jsp"/>

<div class="form-wrapper">
    <div class="registration-form">
        <h1 class="h3 mb-3 font-weight-normal text-center">Favorite-Motors</h1>

        <form class="needs-validation" action="controller" method="post">

            <!-- hidden input -->
            <input type="hidden" name="command" value="registration"/>
            <!-- /hidden input -->

            <div class="form-group">
                <label for="login"><fmt:message key="registration.label.username" bundle="${rb}"/>: </label>
                <input type="text" id="login" name="username" class="form-control" required autofocus>
            </div>

            <div class="form-group">
                <label for="password"><fmt:message key="registration.label.password" bundle="${rb}"/>: </label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="surname"><fmt:message key="registration.label.surname" bundle="${rb}"/>: </label>
                <input type="text" id="surname" name="surname" class="form-control" required autofocus>
            </div>

            <div class="form-group">
                <label for="name"><fmt:message key="registration.label.name" bundle="${rb}"/>: </label>
                <input type="text" id="name" name="name" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="email"><fmt:message key="registration.label.email" bundle="${rb}"/>: </label>
                <input type="email" id="email" name="email" class="form-control" required autofocus>
            </div>

            <div class="form-group">
                <label for="phoneNumber"><fmt:message key="registration.label.phoneNumber" bundle="${rb}"/>: </label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" required>
            </div>

            <div>
                <p class="text-center text-muted small">
                    <fmt:message key="registration.label.accountAvailability" bundle="${rb}"/>
                    <a href="${pageContext.request.contextPath}/jsp/registration.jsp">
                        <fmt:message key="registration.link.signIn" bundle="${rb}"/>
                    </a>
                </p>
            </div>

            <div class="text-danger">
                <p>${existingLogin}</p>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <fmt:message key="registration.button.registration" bundle="${rb}"/>
            </button>
        </form>
    </div>

</div>

<jsp:include page="../fragments/footer.jsp"/>

<%-- scripts --%>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<%-- /scripts --%>
</body>
</html>