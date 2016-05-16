<%--
  Created by IntelliJ IDEA.
  User: WRKSPACE2
  Date: 3/30/2016
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/global.css"/>
    <title>Реєстрація</title>
</head>

<body>
<% if (request.getUserPrincipal() != null) response.sendRedirect("/");%>
<header>
    <jsp:include page="partial/header.jsp" />
</header>
<div class="content">
    <c:if test="${not empty error}">
        <div class="error"><%= request.getAttribute("error").toString() %>
        </div>
    </c:if>
    <div class="content-title">
        <h2>Реєстрація</h2>
        <h4>Зареєструватися в системі</h4>
    </div>
    <div class="login-form">
        <form action="signup" method="post">
            <label>Ім'я користувача</label><br>
            <input type="text" name="username" placeholder="Username" value="${not empty savedValues.username ? savedValues.username[0] : ''}"/><br>
            <label>Email</label><br>
            <input type="email" name="email" placeholder="Email"  value="${not empty savedValues.email ? savedValues.email[0] : ''}"/><br>
            <label>Дата народження</label><br>
            <input type="date" name="bDate"  value="${not empty savedValues.bDate ? savedValues.bDate[0] : ''}"/><br>
            <label>ПІБ</label><br>
            <input type="text" name="fullName" placeholder="ПІБ"  value="${not empty savedValues.fullName ? savedValues.fullName[0] : ''}"/><br>
            <label>Пароль</label><br>
            <input type="password" name="password" placeholder="Password"/><br>
            <label>Пароль ще раз</label><br>
            <input type="password" name="password_2" placeholder="Password"/><br><br>
            <input type="submit" value="Реєстрація"/>
        </form>
    </div>
</div>
<footer>
    <jsp:include page="${pageContext.request.contextPath}/partial/footer.jsp"/>
</footer>
</body>
</html>