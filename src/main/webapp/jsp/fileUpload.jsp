<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="editCar.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>
<body class="site">

<jsp:include page="../fragments/adminHeader.jsp"/>

<main class="site-content">
    <div class="align-items-center">
        <div class="col-4">
            <form action="image-upload" enctype="multipart/form-data" method="post">

                <c:if test="${car != null}">
                    <input type="hidden" class="form-control" name="carId" value="${car.carId}">
                </c:if>

                <div class="mb-3 custom-file">
                    <label for="carImageUploader" class="custom-file-label" data-browse="Search">
                        <fmt:message key="editCar.label.imageInput" bundle="${rb}"/>
                    </label>
                    <input type="file" id="carImageUploader" name="carImageUploader"
                           class="custom-file-input" accept="image/*" size="50" required>
                </div>

                <button class="btn btn-primary btn-lg btn-block" type="submit">
                    <fmt:message key="editCar.button.submit" bundle="${rb}"/>
                </button>
            </form>
        </div>

        <div>
            <h3 class="text-info"><c:out value="${requestScope.uploadResult}"/></h3>
        </div>
    </div>
</main>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/check-file-extension.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/change-file-input-title.js"/>"></script>
<!-- /scripts -->
</body>
</html>