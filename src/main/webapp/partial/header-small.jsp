<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="header-small">
    <div id="head-bar">
        <a href="/"><img id="logo" src="http://placehold.it/150x30" title="Інформаційна Система Підтримки Громадських Ініціатив" alt="logo"/></a>
        <a class="link-button" href="${pageContext.request.contextPath}/newProject">Запропонувати проект</a>
        <div class="float-right">
            <% if (request.isUserInRole("ADMINISTRATOR")) {%>
            <a class="head-link" href="${pageContext.request.contextPath}/admin"> Панель адміністратора </a>
            <%}%>
            <a class="head-link" href="${pageContext.request.contextPath}/explore">Список проектів</a>
            <% if (request.getRemoteUser() != null) {%>
            <a class="head-link profile-link"
               href="${pageContext.request.contextPath}/user/profile"><%= request.getRemoteUser()%>
            </a>
            <a class="head-link" href="${pageContext.request.contextPath}/logout">Вихід</a>
            <%} else {%>
            <a class="head-link" href="${pageContext.request.contextPath}/login">Вхід</a><a class="head-link" href="${pageContext.request.contextPath}/signup">Реєстрація</a>
            <%}%>
        </div>
    </div>
</div>
