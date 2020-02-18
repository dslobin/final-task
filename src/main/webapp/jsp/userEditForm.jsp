<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>
<fmt:setBundle basename="properties.pageContent" var="rb"/>

<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="editUser.head.title" bundle="${rb}"/></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
</head>
<body>

<jsp:include page="../fragments/adminHeader.jsp"/>

<div class="form-wrapper mb-3">

    <div class="registration-form">
        <h1 class="h3 mb-3 font-weight-normal text-center">Favorite-Motors</h1>

        <form class="needs-validation" action="controller" method="post">

            <!-- hidden input -->
            <c:if test="${user == null}">
                <input type="hidden" name="command" value="add_user"/>
            </c:if>

            <c:if test="${user != null}">
                <input type="hidden" name="command" value="edit_user"/>
            </c:if>
            <!-- /hidden input -->

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

            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <fmt:message key="editUser.button.submit" bundle="${rb}"/>
            </button>
        </form>
    </div>

</div>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/main.js"/>"></script>
<!-- /scripts -->
</body>
</html>