<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="properties/pageContent" var="rb"/>

<footer class="font-small bg-light teal pt-4 border-top border-bottom">
    <!-- Copyright -->
    <div class="footer-copyright text-center py-3">
        <p>&copy; 2019-2020, <fmt:message key="footer.label.info" bundle="${rb}"/></p>
    </div>
    <!-- Copyright -->
</footer>