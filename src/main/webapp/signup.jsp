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
    <link rel="stylesheet" href="global.css" />
    <title>Реєстрація</title>
</head>
<body>
<header>
    <div id="header">
        <div id="head-bar">

            <img id="logo" src="http://placehold.it/150x30" />
            <a class="link-button" href="new.html">Запропонувати проект</a>
            <div class="float-right">
                <a class="head-link" href="explore.html">Список проектів</a>
                <a class="head-link" href="help.html">Допомога</a>
                <a class="head-link" href="login.html">Вхід</a><a class="head-link" href="/signup">Реєстрація</a>
            </div>
            <div id="slogan">
                <h1 class="slogan-title">Інформаційна Система Підтримки<br>Громадських Ініціатив</h1>
                <h4 class="slogan-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                    Fusce hendrerit enim eget sem sodales, at semper...</h4>
            </div>
        </div>
    </div>
</header>
<div class="content">
    <c:if test="${not empty error}">
        <div class="error"><%= request.getAttribute("error").toString() %></div>
    </c:if>
    <div class="content-title">
        <h2>Реєстрація</h2>
        <h4>Зареєструватися в системі</h4>
    </div>
    <div class="login-form">
        <form action="signup" method="post">
            <label>Ім'я користувача</label><br>
            <input type="text" name="username" placeholder="Username" /><br>
            <label>Email</label><br>
            <input type="email" name="email" placeholder="Email" /><br>
            <label>Дата народження</label><br>
            <input type="date" name="bDate" /><br>
            <label>ПІБ</label><br>
            <input type="text" name="fullName" placeholder="ПІБ" /><br>
            <label>Пароль</label><br>
            <input type="text" name="password" placeholder="Password" /><br>
            <label>Пароль ще раз</label><br>
            <input type="text" name="password_2" placeholder="Password" /><br><br>
            <input type="submit" value="Реєстрація" />
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