<%--
  Created by IntelliJ IDEA.
  User: WRKSPACE2
  Date: 4/13/2016
  Time: 9:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/global.css"/>
    <title>UI Prototype</title>
</head>
<body>
<% if (request.getUserPrincipal() != null) response.sendRedirect("/");%>
<header>
    <jsp:include page="partial/header.jsp" />
</header>
<div class="content">
    <c:if test="${not empty error}">
        <div class="error"><%= request.getAttribute("error").toString() %></div>
    </c:if>
    <div class="content-title">
        <h2>Вхід</h2>
        <h4>Вхід на сайт для зареєстрованих користувачів</h4>
    </div>
    <div class="login-form">
        <form action="j_security_check" method="POST">
            <label>Ім'я користувача</label><br>
            <input type="text" name="j_username" placeholder="Username" value="${not empty savedValues.j_username ? savedValues.j_username[0] : ''}"/><br>
            <label>Пароль</label><br>
            <input type="password" name="j_password" placeholder="Password"/><br>
            <input type="checkbox" name="category">Запам'ятати<br><br>
            <input type="submit" value="Вхід" />
        </form>
    </div>
</div>
<footer>
    <div id="footer">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
        Fusce hendrerit enim eget sem sodales, at semper...
    </div>

</footer>
</body>
</html>