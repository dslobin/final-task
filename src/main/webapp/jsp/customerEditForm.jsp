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

<div class="form-wrapper mb-3 ml-3 col-4">
    <form class="needs-validation" action="controller" method="post">

        <!-- hidden input -->
        <c:if test="${customer == null}">
            <input type="hidden" name="command" value="add_customer"/>
        </c:if>

        <c:if test="${customer != null}">
            <input type="hidden" name="command" value="edit_customer"/>
        </c:if>
        <!-- /hidden input -->

        <div class="user-data">

            <c:if test="${user != null}">
                <input type="hidden" class="form-control" name="userId" value="${user.userId}">
            </c:if>

            <div class="form-group">
                <label for="username"><fmt:message key="editUser.label.username" bundle="${rb}"/>: </label>
                <input type="text" id="username" name="username" value="${user.username}" class="form-control"
                       minlength="1" maxlength="30" required autofocus>
            </div>

            <div class="form-group">
                <label for="password"><fmt:message key="editUser.label.password" bundle="${rb}"/>: </label>
                <input type="password" id="password" name="password" value="${user.password}" class="form-control"
                       minlength="6" maxlength="30" required>
            </div>

            <div class="form-group">
                <label for="userStatus"><fmt:message key="editUser.label.status" bundle="${rb}"/>: </label>
                <select id="userStatus" name="userStatus" class="form-control">
                    <c:if test="${user == null}">
                        <c:forEach items="${userStatusArray}" var="status">
                            <option>${status}</option>
                        </c:forEach>
                    </c:if>

                    <c:if test="${user != null}">
                        <c:forEach items="${userStatusArray}" var="status">
                            <c:if test="${status == user.status}">
                                <option selected>${status}</option>
                            </c:if>
                            <c:if test="${status != user.status}">
                                <option>${status}</option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>

        <div class="customer-data">

            <c:if test="${customer != null}">
                <input type="hidden" class="form-control" name="customerId" value="${customer.customerId}">
            </c:if>

            <div class="form-group">
                <label for="surname"><fmt:message key="editCustomer.label.surname" bundle="${rb}"/>: </label>
                <input type="text" id="surname" name="surname" class="form-control"
                       minlength="2" maxlength="30" value="${customer.surname}" required autofocus>
            </div>

            <div class="form-group">
                <label for="name"><fmt:message key="editCustomer.label.name" bundle="${rb}"/>: </label>
                <input type="text" id="name" name="name" class="form-control"
                       minlength="2" maxlength="30" value="${customer.name}" required>
            </div>

            <div class="form-group">
                <label for="email"><fmt:message key="editCustomer.label.email" bundle="${rb}"/>: </label>
                <input type="email" id="email" name="email" class="form-control"
                       pattern="^([\p{L}-\.]+){1,64}@([\p{L}&&[^_]]+){2,255}.[a-z]{2,}$" maxlength="120"
                       value="${customer.email}" required>
            </div>

            <div class="form-group">
                <label for="phoneNumber"><fmt:message key="editCustomer.label.phoneNumber" bundle="${rb}"/>: </label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control"
                       pattern="^(((8[\\- ]?)(\\(?\\d{3}\\)?[\\- ]?))|(\\+375[\\-]?)(\\(?\\d{2}\\)?[\\- ]?))?[\\d]{3}[\\-]?[\\d]{2}[\\-]?[\\d]{2}$"
                       value="${customer.phoneNumber}" required>
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
<script type="text/javascript" src="<c:url value="/static/js/main.js"/>"></script>
<!-- /scripts -->
</body>
</html>