<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>

<html lang="ru">
<head>
    <title><fmt:message key="editService.head.title" bundle="${rb}"/></title>
    <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/style.css" />" rel="stylesheet"/>
</head>

<body>

<jsp:include page="/fragments/adminHeader.jsp"/>

<div class="col-md-8 order-md-1">
    <h4 class="mb-3">Service editing</h4>

    <form class="needs-validation" action="controller" method="post">

        <!-- hidden input -->
        <c:if test="${autoShowService == null}">
            <input type="hidden" name="command" value="add_service"/>
        </c:if>
        <c:if test="${autoShowService != null}">
            <input type="hidden" name="command" value="edit_service"/>
        </c:if>
        <!-- /hidden input -->

        <div class="row">
            <div class="col-md-6 align-top">
                <c:if test="${autoShowService != null}">
                    <input type="hidden" class="form-control" name="serviceId" value="${autoShowService.serviceId}">
                </c:if>

                <div class="mb-3">
                    <label for="serviceTitle"><fmt:message key="editService.label.serviceTitle" bundle="${rb}"/></label>
                    <input type="text" class="form-control" id="serviceTitle" name="serviceTitle"
                           value="${autoShowService.title}" minlength="1" maxlength="512" required>
                    <div class="invalid-feedback">
                        Valid service title is required.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="serviceCost"><fmt:message key="editService.label.cost" bundle="${rb}"/></label>
                    <input type="text" class="form-control" id="serviceCost" name="serviceCost"
                           pattern="(\d+\.\d+)|(\d+)" value="${autoShowService.cost}" required>
                    <div class="invalid-feedback">
                        Valid service cost is required.
                    </div>
                </div>

            </div>
        </div>

        <div class="mb-3">
            <label for="serviceDescription">
                <fmt:message key="editService.label.serviceDescription" bundle="${rb}"/>
            </label>
            <textarea class="form-control" id="serviceDescription" cols="40" rows="3" name="serviceDescription"
                      minlength="1" maxlength="2048" required>
                ${autoShowService.description}
            </textarea>
            <div class="invalid-feedback">
                Service description is required
            </div>
        </div>

        <button class="btn btn-primary btn-lg btn-block" type="submit">
            <fmt:message key="editService.button.submit" bundle="${rb}"/>
        </button>
    </form>
</div>

<jsp:include page="/fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<!-- /scripts -->
</body>
</html>