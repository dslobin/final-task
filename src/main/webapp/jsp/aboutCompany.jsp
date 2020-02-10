<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties/pageContent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="editUser.head.title" bundle="${rb}"/></title>
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

<div class="container page-wrap">
    <div>
        <h2><fmt:message key="aboutCompany.text.contacts" bundle="${rb}"/></h2>
    </div>
    <div class="row company-info-content-wrapper">
        <div class="col-5 contact-details">
            <h4><fmt:message key="aboutCompany.text.phoneNumber" bundle="${rb}"/></h4>
            <div>
                <div>
                    <strong><fmt:message key="aboutCompany.text.salesDepartment" bundle="${rb}"/></strong>
                    <p><a href="#">+375 29 <strong>111-11-11</strong></a> Velcom</p>
                    <p><a href="#">+375 33 <strong>222-22-22</strong></a> MTC</p>
                    <p><a href="#">+375 17 <strong>333-33-33</strong></a>
                        <fmt:message key="aboutCompany.text.telephoneOperator" bundle="${rb}"/>
                    </p>
                </div>

            </div>
            <div>
                <div>
                    <strong><fmt:message key="aboutCompany.text.commission" bundle="${rb}"/></strong>
                    <p><a href="#">+375 29 <strong>1234-567</strong></a> Velcom</p>
                    <p><a href="#">+375 33 <strong>7654-321</strong></a> MTC</p>
                </div>

                <div>
                    <strong><fmt:message key="aboutCompany.text.exchange" bundle="${rb}"/></strong>
                    <p><a href="#">+375 29 <strong>456-78-90</strong></a> Velcom</p>
                    <p><a href="#">+375 33 <strong>111-22-33</strong></a> MTC</p>
                </div>
            </div>
        </div>

        <div class="col-4 mx-2 contact-details">
            <div>
                <h4><fmt:message key="aboutCompany.text.workingHours" bundle="${rb}"/></h4>
                <p><fmt:message key="aboutCompany.text.monday" bundle="${rb}"/> <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.tuesday" bundle="${rb}"/> <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.wednesday" bundle="${rb}"/> <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.thursday" bundle="${rb}"/> <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.friday" bundle="${rb}"/> <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.saturday" bundle="${rb}"/> <span>9.00 - 18.00</span></p>
                <p><fmt:message key="aboutCompany.text.sunday" bundle="${rb}"/> <span>
                    <fmt:message key="aboutCompany.text.dayOff" bundle="${rb}"/></span>
                </p>
            </div>
        </div>

        <div class="col-2 contact-details">
            <div>
                <h4>E-mail</h4>
                <p>info@favourite-motors.by</p>
            </div>
            <div>
                <h4><fmt:message key="aboutCompany.text.socialNetworks" bundle="${rb}"/></h4>
                <div>
                    <a href="#"><i class="fa fa-facebook"></i></a>
                    <a href="#"><i class="fa fa-vk"></i></a>
                    <a href="#"><i class="fa fa-instagram"></i></a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>