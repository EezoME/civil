<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header id="small-header">
    <div class="container-fluide">
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNav">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">
                        <img alt="sss" width="150" height="30" src="http://placehold.it/150x30"/>
                    </a>
                    <a href="${pageContext.request.contextPath}/newProject">
                        <input type="button" class="btn btn-primary navbar-btn"
                               value="Запропонувати проект"/>
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="myNav">
                    <ul class="nav navbar-nav navbar-right">
                        <% if (request.isUserInRole("ADMINISTRATOR")) {%>
                        <li><a href="${pageContext.request.contextPath}/admin">Панель адміністратора</a></li>
                        <%}%>
                        <li><a href="${pageContext.request.contextPath}/explore">Список проектів</a></li>
                        <% if (request.getRemoteUser() != null) {%>
                        <li><a href="${pageContext.request.contextPath}/user/profile"><%= request.getRemoteUser()%>
                        </a></li>
                        <li><a href="${pageContext.request.contextPath}/logout">Вихід</a></li>
                        <%} else {%>
                        <li><a href="${pageContext.request.contextPath}/login">Вхід</a></li>
                        <li><a href="${pageContext.request.contextPath}/signup">Реєстрація</a></li>
                        <%}%>

                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>
