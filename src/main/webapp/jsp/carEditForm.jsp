<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>
<%--${language}--%>
<html>
<head>
    <title><fmt:message key="editCar.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>

<body lang="ru">

<jsp:include page="../fragments/adminHeader.jsp"/>

<div class="col-md-8 order-md-1">
    <h4 class="mb-3">Car Information</h4>
    <form class="needs-validation" action="controller" method="post">

        <div class="mb-3 custom-file">
            <label for="carImage" class="custom-file-label" data-browse="Search">
                <fmt:message key="editCar.label.imageInput" bundle="${rb}"/>
            </label>
            <input type="file" id="carImage" class="custom-file-input" accept="image/*" size="50" required>
        </div>

        <div class="row">
            <div class="col-md-6 align-top">
                <div class="mb-3">
                    <label for="firstName">
                        <fmt:message key="editCar.label.model" bundle="${rb}"/>
                    </label>
                    <input type="text" class="form-control" id="firstName" required>
                </div>

                <div class="mb-3">
                    <label for="mileage">
                        <fmt:message key="editCar.label.mileage" bundle="${rb}"/>
                    </label>
                    <input type="number" class="form-control" id="mileage" required>
                </div>

                <div class="mb-3">
                    <label for="fuelType">
                        <fmt:message key="editCar.label.fuelType" bundle="${rb}"/>
                    </label>
                    <select type="text" class="form-control" id="fuelType" required></select>
                </div>

                <div class="mb-3">
                    <label for="bodyType">
                        <fmt:message key="editCar.label.bodyType" bundle="${rb}"/>
                    </label>
                    <select type="text" class="form-control" id="bodyType"></select>
                </div>

                <div class="mb-3">
                    <label for="volume">
                        <fmt:message key="editCar.label.volume" bundle="${rb}"/>
                    </label>
                    <input type="number" class="form-control" id="volume">
                </div>

                <div class="mb-3">
                    <label for="transmission">
                        <fmt:message key="editCar.label.transmission" bundle="${rb}"/>
                    </label>
                    <input type="text" class="form-control" id="transmission">
                </div>
            </div>

            <div class="col-md-6 align-top">
                <div class="mb-3">
                    <label for="driveUnit">
                        <fmt:message key="editCar.label.driveUnit" bundle="${rb}"/>
                    </label>
                    <input type="text" class="form-control" id="driveUnit" required>
                </div>

                <div class="mb-3">
                    <label for="color">
                        <fmt:message key="editCar.label.color" bundle="${rb}"/>
                    </label>
                    <select type="text" class="form-control" id="color" required></select>
                </div>

                <div class="mb-3">
                    <label for="issueYear">
                        <fmt:message key="editCar.label.issueYear" bundle="${rb}"/>
                    </label>
                    <input type="number" class="form-control" id="issueYear" required>
                </div>

                <div class="mb-3">
                    <label for="price">
                        <fmt:message key="editCar.label.price" bundle="${rb}"/>, $
                    </label>
                    <input type="number" class="form-control" id="price" required>
                </div>

                <div class="mb-3">
                    <label for="status">
                        <fmt:message key="editCar.label.status" bundle="${rb}"/>
                    </label>
                    <select type="text" class="form-control" id="status" required></select>
                </div>
            </div>
        </div>

        <div class="mb-3">
            <label for="carDescription"><fmt:message key="editCar.label.additionalInformation" bundle="${rb}"/></label>
            <textarea class="form-control" id="carDescription" cols="40" rows="3" required></textarea>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">
            <fmt:message key="editCar.button.submit" bundle="${rb}"/>
        </button>
    </form>
</div>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/main.js"/>"></script>
<!-- /scripts -->
</body>
</html>
