<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="header-small">
    <div id="head-bar">
        <img id="logo" src="http://placehold.it/150x30"/>
        <a class="link-button" href="newProject">Запропонувати проект</a>
        <div class="float-right">
            <a class="head-link" href="/explore.html">Список проектів</a>
            <a class="head-link" href="/help.html">Допомога</a>
            <% if (request.getRemoteUser() != null) {%>
            <a class="head-link profile-link" href="/profile"><%= request.getRemoteUser()%>
            </a>
            <a class="head-link" href="/logout">Вихід</a>
            <%} else {%>
            <a class="head-link" href="/login">Вхід</a><a class="head-link" href="/signup">Реєстрація</a>
            <%}%>
        </div>
    </div>
</div>
