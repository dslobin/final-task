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

<body class="site">

<jsp:include page="../fragments/guestHeader.jsp"/>

<main class="site-content">
    <div class="mb-3">
        <div class="registration-form">
            <h1 class="h3 mb-3 font-weight-normal text-center">Favorite-Motors</h1>

            <form class="needs-validation" action="controller" method="post">

                <!-- hidden input -->
                <input type="hidden" name="command" value="registration"/>
                <!-- /hidden input -->

                <div class="form-group">
                    <label for="login"><fmt:message key="registration.label.username" bundle="${rb}"/>: </label>
                    <input type="text" id="login" name="username" class="form-control"
                           minlength="1" maxlength="30" required autofocus>
                </div>

                <div class="form-group">
                    <label for="password"><fmt:message key="registration.label.password" bundle="${rb}"/>: </label>
                    <input type="password" id="password" name="password" class="form-control"
                           minlength="6" maxlength="30" required>
                </div>

                <div class="form-group">
                    <label for="surname"><fmt:message key="registration.label.surname" bundle="${rb}"/>: </label>
                    <input type="text" id="surname" name="surname" class="form-control"
                           minlength="2" maxlength="30" required autofocus>
                </div>

                <div class="form-group">
                    <label for="name"><fmt:message key="registration.label.name" bundle="${rb}"/>: </label>
                    <input type="text" id="name" name="name" class="form-control"
                           minlength="2" maxlength="30" required>
                </div>

                <div class="form-group">
                    <label for="email"><fmt:message key="registration.label.email" bundle="${rb}"/>: </label>
                    <input type="email" id="email" name="email" class="form-control"
                           pattern="^([\p{L}\d-\.]+){1,64}@([\p{L}&&[^_]]+){2,255}.[a-z]{2,}$" maxlength="120"
                           required autofocus>
                </div>

                <div class="form-group">
                    <label for="phoneNumber"><fmt:message key="registration.label.phoneNumber"
                                                          bundle="${rb}"/>: </label>
                    <input type="text" id="phoneNumber" name="phoneNumber" class="form-control"
                           pattern="^(((8[\\- ]?)(\\(?\\d{3}\\)?[\\- ]?))|(\\+375[\\-]?)(\\(?\\d{2}\\)?[\\- ]?))?[\\d]{3}[\\-]?[\\d]{2}[\\-]?[\\d]{2}$"
                           required>
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
                    <p>${requestScope.existingLogin}</p>
                    <p>${requestScope.invalidCustomer}</p>
                </div>

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    <fmt:message key="registration.button.registration" bundle="${rb}"/>
                </button>
            </form>
        </div>
    </div>
</main>

<jsp:include page="../fragments/footer.jsp"/>

<%-- scripts --%>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/validation.js"/>"></script>
<%-- /scripts --%>
</body>
</html>