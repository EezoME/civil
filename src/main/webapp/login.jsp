<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <title>UITest</title>
</head>

<body>
<div id="wrap">
    <jsp:include page="partial/header.jsp"/>
    <div class="container">
        <div id="login-container">
            <div class="row">
                <c:if test="${not empty error}">
                    <div class="error"><%= request.getAttribute("error").toString() %>
                    </div>
                </c:if>
                <h2>Вхід</h2>
                <h4>Вхід на сайт для зареєстрованих користувачів</h4>
            </div>
            <div class="row">
                <form action="j_security_check" method="POST">
                    <div class="form-group">
                        <label for="login">Ім'я користувача</label>
                        <input type="text" class="form-control" name="j_username" id="login" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input type="password" class="form-control" name="j_password" id="password"
                               placeholder="Password">
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Запам'ятати
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary form-button">Вхід</button>
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