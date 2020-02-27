<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="editOrder.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
</head>

<body class="site">

<jsp:include page="../fragments/clientHeader.jsp"/>

<main class="site-content">
    <div class="col-md-8 order-md-1 ml-2">
        <h4 class="mb-3"><fmt:message key="editOrder.text.repairSignUp" bundle="${rb}"/></h4>
        <form class="needs-validation" novalidate>

            <!-- hidden input -->
            <input type="hidden" name="command" value="create_order"/>
            <!-- /hidden input -->

            <div class="row">
                <div class="col-md-6 align-top">
                    <h5 class="mb-3"><fmt:message key="editOrder.text.personalDetails" bundle="${rb}"/>:</h5>

                    <div class="mb-3">
                        <label for="firstName"><fmt:message key="editOrder.label.firstName" bundle="${rb}"/>:
                            <c:out value="${customer.name}"/></label>
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               value="${customer.name}" hidden>
                    </div>

                    <div class="mb-3">
                        <label for="lastName"><fmt:message key="editOrder.label.lastName" bundle="${rb}"/>:
                            <c:out value="${customer.surname}"/></label>
                        <input type="text" class="form-control" id="lastName" name="lastName"
                               value="${customer.surname}" hidden>
                    </div>
                </div>

                <div class="col-md-6 align-top">
                    <h5 class="mb-3"><fmt:message key="editOrder.text.serviceDetails" bundle="${rb}"/>:</h5>

                    <input type="text" class="form-control" id="serviceId" name="serviceId"
                           value="${service.serviceId}" hidden>

                    <div class="mb-3">
                        <label for="serviceTitle"><fmt:message key="editOrder.label.serviceTitle" bundle="${rb}"/>:
                            <c:out value="${service.title}"/></label>
                        <input type="text" class="form-control" id="serviceTitle" name="serviceTitle"
                               value="${service.title}" hidden>
                    </div>

                    <div class="mb-3">
                        <label for="serviceCost"><fmt:message key="editOrder.label.serviceCost" bundle="${rb}"/>:
                            <c:out value="${service.cost}"/></label>
                        <input type="text" class="form-control" id="serviceCost" name="serviceCost"
                               value="${service.cost}" hidden>
                    </div>
                </div>
            </div>

            <div class="row">
                <h4><fmt:message key="editOrder.label.serviceTiming" bundle="${rb}"/></h4>
            </div>

            <div class="form-group">
                <label for="serviceTime">
                    <fmt:message key="editOrder.label.desiredServiceTime" bundle="${rb}"/>:
                </label>
                <input type="time" class="form-control" id="serviceTime" name="serviceTime" required>
            </div>

            <div class="form-group">
                <label for="serviceDate">
                    <fmt:message key="editOrder.label.desiredServiceDate" bundle="${rb}"/>:
                </label>
                <input type="date" class="form-control" id="serviceDate" name="serviceDate" required>
            </div>

            <hr class="mb-4">
            <div class="custom-control custom-checkbox mb-3">
                <label class="custom-control-label" for="dataProcessing">
                    <fmt:message key="editOrder.text.consentDataProcessing" bundle="${rb}"/>
                </label>
                <input type="checkbox" class="custom-control-input" id="dataProcessing" required>
            </div>

            <button class="btn btn-primary btn-lg btn-block" type="submit">
                <fmt:message key="editOrder.button.submit" bundle="${rb}"/>
            </button>
        </form>
    </div>
</main>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/date-and-time-restriction.js"/>"></script>
<!-- /scripts -->
</body>
</html>