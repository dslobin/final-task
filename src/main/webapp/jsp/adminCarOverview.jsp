<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="carOverview.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>

<body>

<jsp:include page="../fragments/adminHeader.jsp"/>

<div class="page-wrap">
    <jsp:useBean id="carList" class="java.util.ArrayList" scope="request"/>
    <c:choose>
        <c:when test="${not empty carList}">

            <div class="">
                <table class="table table-borderless table-striped">
                    <tbody id="page">
                    <c:forEach var="car" items="${carList}">
                        <tr class="table-row">
                            <td width="200px">
                                <img class="card-img-top"
                                     src="${pageContext.request.contextPath}<c:out value="${car.imageUrl}"/>"
                                     alt="Car image" width="200" height="80">
                            </td>
                            <td>
                                <p><c:out value="${car.issueYear}"/></p>
                                <p><strong><c:out value="${car.price}"/></strong>, $</p>
                            </td>
                            <td>
                                <p class="text-info"><strong><c:out value="${car.model}"/></strong></p>
                                <p>
                                    <c:out value="${car.transmission}"/>,
                                    <c:out value="${car.mileage}"/> km,
                                    <c:out value="${car.fuelType}"/>,
                                    <c:out value="${car.bodyType}"/>
                                </p>
                                <p class="text-muted"><c:out value="${car.description}"/></p>
                            </td>
                            <td>
                                <a class="mr-2" href="controller?command=get_car_edit_page&carId=${car.carId}">
                                    <fmt:message key="carOverview.label.edit" bundle="${rb}"/>
                                </a>

                                <a class="text-warning ml-2" href="controller?command=upload_car_image&carId=${car.carId}">
                                    <fmt:message key="carOverview.label.addPhoto" bundle="${rb}"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="ml-3">
                <jsp:include page="../fragments/pagination.jsp"/>
            </div>

        </c:when>
        <c:otherwise>
            <h2><fmt:message key="carOverview.text.emptyList" bundle="${rb}"/></h2>
        </c:otherwise>
    </c:choose>

    <div class="col-3">
        <form method="post" action="controller">

            <!-- hidden input -->
            <input type="hidden" name="command" value="get_car_add_page"/>
            <!-- /hidden input -->

            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <fmt:message key="carOverview.button.submit" bundle="${rb}"/>
            </button>
        </form>
    </div>

</div>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/pagination.js"/>"></script>
<!-- /scripts -->
</body>
</html>