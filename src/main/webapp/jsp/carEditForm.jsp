<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <div class="col-md-8 order-md-1">
        <form class="needs-validation mb-3" action="controller" method="post">
            <!-- hidden input -->
            <c:if test="${car == null}">
                <input type="hidden" name="command" value="add_car"/>
            </c:if>
            <c:if test="${car != null}">
                <input type="hidden" name="command" value="edit_car"/>
            </c:if>
            <!-- /hidden input -->

            <c:if test="${car != null}">
                <input type="hidden" class="form-control" name="carId" value="${car.carId}">
            </c:if>

            <div class="row">
                <div class="col-md-6 align-top">
                    <div class="mb-3">
                        <label for="model"><fmt:message key="editCar.label.model" bundle="${rb}"/>:</label>

                        <input type="text" class="form-control" id="model" name="model"
                               minlength="1" maxlength="45" value="${car.model}" required>
                    </div>

                    <div class="mb-3">
                        <label for="mileage"><fmt:message key="editCar.label.mileage" bundle="${rb}"/>:</label>

                        <input type="number" class="form-control" id="mileage" name="mileage"
                               min="0" value="${car.mileage}" required>
                    </div>

                    <div class="mb-3">
                        <label for="fuelType"><fmt:message key="editCar.label.fuelType" bundle="${rb}"/>:</label>

                        <select type="text" class="form-control" id="fuelType" name="fuelType" required>
                            <c:if test="${car == null}">
                                <c:forEach items="${fuelTypeList}" var="fuel">
                                    <option>${fuel}</option>
                                </c:forEach>
                            </c:if>

                            <c:if test="${car != null}">
                                <c:forEach items="${fuelTypeList}" var="fuel">
                                    <c:if test="${fuel == car.fuelType}">
                                        <option selected>${fuel}</option>
                                    </c:if>
                                    <c:if test="${fuel != car.fuelType}">
                                        <option>${fuel}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="bodyType"><fmt:message key="editCar.label.bodyType" bundle="${rb}"/>:</label>

                        <select type="text" class="form-control" id="bodyType" name="bodyType">
                            <c:if test="${car == null}">
                                <c:forEach items="${bodyTypeList}" var="body">
                                    <option>${body}</option>
                                </c:forEach>
                            </c:if>

                            <c:if test="${car != null}">
                                <c:forEach items="${bodyTypeList}" var="body">
                                    <c:if test="${body == car.bodyType}">
                                        <option selected>${body}</option>
                                    </c:if>
                                    <c:if test="${body != car.bodyType}">
                                        <option>${body}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="volume"><fmt:message key="editCar.label.volume" bundle="${rb}"/>:</label>

                        <input type="number" class="form-control" id="volume" name="volume"
                               min="1000" max="8400" step="100" value="${car.volume}">
                    </div>

                    <div class="mb-3">
                        <label for="transmission"><fmt:message key="editCar.label.transmission" bundle="${rb}"/>:</label>

                        <input type="text" class="form-control" id="transmission" name="transmission"
                               minlength="1" maxlength="45" value="${car.transmission}">
                    </div>
                </div>

                <div class="col-md-6 align-top">
                    <div class="mb-3">
                        <label for="driveUnit"><fmt:message key="editCar.label.driveUnit" bundle="${rb}"/>:</label>

                        <input type="text" class="form-control" id="driveUnit" name="driveUnit"
                               value="${car.driveUnit}" required>
                    </div>

                    <div class="mb-3">
                        <label for="color"><fmt:message key="editCar.label.color" bundle="${rb}"/>:</label>

                        <select type="text" class="form-control" id="color" name="color" required>
                            <c:if test="${car == null}">
                                <c:forEach items="${colorList}" var="color">
                                    <option>${color.code}</option>
                                </c:forEach>
                            </c:if>

                            <c:if test="${car != null}">
                                <c:forEach items="${colorList}" var="color">
                                    <c:if test="${color == car.color}">
                                        <option selected>${color.code}</option>
                                    </c:if>
                                    <c:if test="${color != car.color}">
                                        <option>${color.code}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="issueYear"><fmt:message key="editCar.label.issueYear" bundle="${rb}"/>:</label>

                        <input type="number" class="form-control" id="issueYear" name="issueYear"
                               min="1960" max="2020" minlength="4" maxlength="4" value="${car.issueYear}" required>
                    </div>

                    <div class="mb-3">
                        <label for="price"><fmt:message key="editCar.label.price" bundle="${rb}"/>, $</label>

                        <input type="text" class="form-control" id="price" name="price"
                               pattern="(\d+\.\d+)|(\d+)" value="${car.price}" required>
                    </div>

                    <div class="mb-3">
                        <label for="saleStatus"><fmt:message key="editCar.label.status" bundle="${rb}"/>:</label>

                        <select type="text" class="form-control" id="saleStatus" name="saleStatus" required>
                            <c:if test="${car == null}">
                                <c:forEach items="${saleStatusList}" var="status">
                                    <option>${status}</option>
                                </c:forEach>
                            </c:if>

                            <c:if test="${car != null}">
                                <c:forEach items="${saleStatusList}" var="status">
                                    <c:if test="${status == car.status}">
                                        <option selected>${status}</option>
                                    </c:if>
                                    <c:if test="${status != car.status}">
                                        <option>${status}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <label for="carDescription"><fmt:message key="editCar.label.additionalInformation"
                                                         bundle="${rb}"/>:</label>
                <textarea class="form-control" id="carDescription" name="carDescription" cols="40" rows="3"
                          minlength="0" maxlength="4096"><c:out value="${car.description}"/></textarea>
            </div>

            <div class="text-danger">
                <p>${requestScope.invalidCar}</p>
            </div>

            <button class="btn btn-primary btn-lg btn-block" type="submit">
                <fmt:message key="editCar.button.submit" bundle="${rb}"/>
            </button>
        </form>
    </div>
</main>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/validation.js"/>"></script>
<!-- /scripts -->
</body>
</html>