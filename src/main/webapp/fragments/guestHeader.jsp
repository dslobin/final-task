<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="properties/pageContent" var="rb"/>

<nav>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
        <h5 class="my-0 mr-md-auto font-weight-normal">Favorite Motors</h5>
        <nav class="my-2 my-md-0 mr-md-3">

            <a class="p-2 text-dark" href="controller?command=get_all_cars">
                <fmt:message key="header.label.cars" bundle="${rb}"/>
            </a>

            <a class="p-2 text-dark" href="controller?command=get_all_services">
                <fmt:message key="header.label.services" bundle="${rb}"/>
            </a>

            <a class="p-2 text-dark" href="controller?command=get_about_company_page">
                <fmt:message key="header.label.contacts" bundle="${rb}"/>
            </a>

            <a class="p-2 text-dark dropdown-toggle" href="#"
               id="navbarDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false" aria-selected="true">
                <fmt:message key="header.label.language" bundle="${rb}"/>
            </a>

            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <%--<a class="dropdown-item" href="controller?command=change_locale&language=ru">Русский</a>
                <a class="dropdown-item" href="controller?command=change_locale&language=by">Беларуская</a>
                <a class="dropdown-item" href="controller?command=change_locale&language=en">English</a>--%>
                <a class="dropdown-item" href="?sessionLocale=ru">Русский</a>
                <a class="dropdown-item" href="?sessionLocale=by">Беларуская</a>
                <a class="dropdown-item" href="?sessionLocale=en">English</a>
            </div>
        </nav>
        <a class="btn btn-outline-success" href="controller?command=get_login_page">Log in</a>
    </div>
</nav>