<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <script src="resources/js/google-analytics-code.js" ></script>
    <title>UITest</title>
</head>

<body>
<div id="wrap">
    <jsp:include page="partial/header.jsp"/>
    <div class="container">
        <div id="login-container">
            <c:if test="${not empty error}">
                <div class="error"><%= request.getAttribute("error").toString() %>
                </div>
            </c:if>
            <div class="row">
                <h2>Реєстрація</h2>
                <h4>Зареєструватися в системі</h4>
            </div>
            <div class="row">
                <form action="signup" method="post">
                    <div class="form-group">
                        <label for="login">Ім'я користувача</label>
                        <input type="text" class="form-control" name="username" id="login" placeholder="Ім'я користувача від 3 до 32 символів"
                               value="${not empty savedValues.username ? savedValues.username[0] : ''}">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" name="email" id="email" placeholder="E-mail"
                               value="${not empty savedValues.email ? savedValues.email[0] : ''}">
                    </div>
                    <div class="form-group">
                        <label for="bdate">Дата народження</label>
                        <input type="date" class="form-control" name="bDate" id="bDate" placeholder="Дата у форматі РРРР-ММ-ДД"
                               value="${not empty savedValues.bDate ? savedValues.bDate[0] : ''}">
                    </div>
                    <div class="form-group">
                        <label for="fullName">ПІБ</label>
                        <input type="text" class="form-control" name="fullName" id="fullName" placeholder="ПІБ"
                               value="${not empty savedValues.fullName ? savedValues.fullName[0] : ''}">
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="Пароль">
                    </div>
                    <div class="form-group">
                        <label for="password_2">Пароль ще раз</label>
                        <input type="password" class="form-control" name="password_2" id="password_2"
                               placeholder="Повторіть пароль">
                    </div>
                    <button type="submit" class="btn btn-primary form-button">Реєстрація</button>
                </form>
            </div>
        </div>
    </div>
    <div id="push"></div>
</div>
<footer>
    <jsp:include page="partial/footer.jsp"/>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>