<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Favorite Motors</title>
    <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/style.css" />" rel="stylesheet"/>
</head>
<body>

<c:choose>
    <c:when test="${userRole == 'ADMIN'}">
        <jsp:include page="../fragments/adminHeader.jsp"/>
    </c:when>

    <c:when test="${userRole == 'CLIENT'}">
        <jsp:include page="../fragments/clientHeader.jsp"/>
    </c:when>
</c:choose>

<input type="hidden" name="command" value="login"/>

<div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light header">
    <div class="col-md-5 p-lg-5 mx-auto my-5">
        <h1 class="display-4 font-weight-normal">Favorite Motors</h1>
        <p class="lead font-weight-normal">Car sales in the Belarus.</p>
    </div>
</div>

<!-- START THE FEATURE -->

<div class="p-3">
    <hr class="feature-divider">

    <div class="row feature">
        <div class="col-md-7">
            <h2 class="feature-heading">First featurette heading. <span class="text-muted">It'll blow your mind.</span>
            </h2>
            <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod
                semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus
                commodo.</p>
        </div>
        <div class="col-md-5">
            <img class="feature-image img-fluid mx-auto"
                 src="${pageContext.request.contextPath}/static/img/news.jpg"
                 alt="Generic placeholder image">
        </div>
    </div>

    <hr class="feature-divider">

    <div class="row feature">
        <div class="col-md-7 order-md-2">
            <h2 class="feature-heading">Oh yeah, it's that good. <span class="text-muted">See for yourself.</span></h2>
            <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod
                semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus
                commodo.</p>
        </div>
        <div class="col-md-5 order-md-1">
            <img class="feature-image img-fluid mx-auto"
                 src="${pageContext.request.contextPath}/static/img/news.jpg"
                 alt="Generic placeholder image">
        </div>
    </div>

    <hr class="feature-divider">

    <div class="row feature">
        <div class="col-md-7">
            <h2 class="feature-heading">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
            <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod
                semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus
                commodo.</p>
        </div>
        <div class="col-md-5">
            <img class="featurette-image img-fluid mx-auto"
                 src="${pageContext.request.contextPath}/static/img/news.jpg"
                 alt="Generic placeholder image">
        </div>
    </div>

    <hr class="feature-divider">
</div>

<!-- /END THE FEATURE -->

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<!-- /scripts -->
</body>
</html>