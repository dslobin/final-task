<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>
<%--${language}--%>

<html>
<head>
    <title><fmt:message key="editCustomer.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>
<body>

<jsp:include page="../fragments/adminHeader.jsp"/>

<div class="form-wrapper mb-3">
    <form class="needs-validation" action="controller" method="post">

        <!-- hidden input -->
        <input type="hidden" name="command" value="edit_customer"/>
        <!-- /hidden input -->

        <div class="customer-data">
            <div class="form-group">
                <label for="surname"><fmt:message key="editCustomer.label.surname" bundle="${rb}"/>: </label>
                <input type="text" id="surname" name="surname" class="form-control" required autofocus>
            </div>

            <div class="form-group">
                <label for="name"><fmt:message key="editCustomer.label.name" bundle="${rb}"/>: </label>
                <input type="text" id="name" name="name" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="email"><fmt:message key="editCustomer.label.email" bundle="${rb}"/>: </label>
                <input type="email" id="email" name="email" class="form-control" required autofocus>
            </div>

            <div class="form-group">
                <label for="phoneNumber"><fmt:message key="editCustomer.label.phoneNumber" bundle="${rb}"/>: </label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" required>
            </div>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="editCustomer.button.submit" bundle="${rb}"/>
        </button>
    </form>
</div>


<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/show-customer-form.js"/>"></script>
<!-- /scripts -->
</body>
</html>