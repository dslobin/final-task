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
<body>

<jsp:include page="../fragments/clientHeader.jsp"/>

<div class="col-md-8 order-md-1">
    <h4 class="mb-3"><fmt:message key="editOrder.text.repairSignUp" bundle="${rb}"/></h4>
    <form class="needs-validation" novalidate>

        <!-- hidden input -->
        <input type="hidden" name="command" value="createOrder"/>
        <!-- /hidden input -->

        <div class="row">
            <div class="col-md-6 align-top">
                <h5 class="mb-3"><fmt:message key="editOrder.text.personalDetails" bundle="${rb}"/>:</h5>

                <div class="mb-3">
                    <label for="firstName"><fmt:message key="editOrder.label.firstName" bundle="${rb}"/></label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="" required>
                </div>

                <div class="mb-3">
                    <label for="lastName"><fmt:message key="editOrder.label.lastName" bundle="${rb}"/></label>
                    <input type="text" class="form-control" id="lastName" name="lastName" value="" required>
                </div>

            </div>

            <div class="col-md-6 align-top">
                <h5 class="mb-3"><fmt:message key="editOrder.text.serviceDetails" bundle="${rb}"/>:</h5>

                <div class="mb-3">
                    <label for="serviceTitle"><fmt:message key="editOrder.label.serviceTitle" bundle="${rb}"/></label>
                    <input type="text" class="form-control" id="serviceTitle" name="serviceTitle" value="" required>
                </div>

                <div class="mb-3">
                    <label for="serviceCost"><fmt:message key="editOrder.label.serviceCost" bundle="${rb}"/></label>
                    <input type="text" class="form-control" id="serviceCost" name="serviceCost" value="" required>
                </div>
            </div>
        </div>

        <hr class="mb-4">

        <div class="row">
            <h2>
                <fmt:message key="editOrder.label.serviceTiming" bundle="${rb}"/>
            </h2>

            <div class="col-md-6">
                <label for="serviceDatePicker">
                    <fmt:message key="editOrder.label.desiredServiceDate" bundle="${rb}"/>
                </label>
                <input type="date" class="form-control" id="serviceDatePicker" name="serviceDatePicker"
                       placeholder="" required>
                <div class="invalid-feedback">
                    Desired service date is required
                </div>
            </div>

            <div class="col-md-6">
                <label for="serviceTimePicker">
                    <fmt:message key="editOrder.label.desiredServiceTime" bundle="${rb}"/>
                </label>
                <input type="time" class="form-control" id="serviceTimePicker" name="serviceTimePicker"
                       placeholder="" required>
                <div class="invalid-feedback">
                    Desired service time is required
                </div>
            </div>
        </div>

        <hr class="mb-4">
        <div class="custom-control custom-checkbox mb-3">
            <input type="checkbox" class="custom-control-input" id="dataProcessing" required>
            <label class="custom-control-label" for="dataProcessing">
                <fmt:message key="editOrder.text.consentDataProcessing" bundle="${rb}"/>
            </label>
        </div>

        <button class="btn btn-primary btn-lg btn-block" type="submit">Send</button>
    </form>
</div>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<!-- /scripts -->
</body>
</html>