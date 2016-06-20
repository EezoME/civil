<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header id="big-header">
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
                        <img alt="logo" class="navbar-logo" width="150" height="30" src="http://placehold.it/150x30"/>
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="myNav">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="${pageContext.request.contextPath}/newProject" class="btn btn-primary navbar-btn">
                                Запропонувати проект
                            </a>
                        </li>
                    </ul>
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
        <div id="slogan">
            <div class="container">
                <h2>Інформаційна Система Підтримки Громадських Ініціатив</h2>
                <h4>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce hendrerit enim eget sem sodales,
                    at semper...</h4>
            </div>
        </div>
    </div>
</header>
