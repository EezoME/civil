<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.rssms.enums.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <title>UITest</title>
</head>

<body>
<div id="wrap">
    <jsp:include page="${pageContext.request.contextPath}/partial/header-small.jsp"/>
    <div class="container">
        <div id="login-container">
            <c:if test="${not empty error}">
                <div class="error"><%= request.getAttribute("error").toString() %>
                </div>
            </c:if>
            <c:if test="${not empty info}">
                <div class="alert alert-success">
                    ${info}
                </div>
            </c:if>
            <div class="row">
                <h2>Додати проект</h2>
                <h4>Помістити новий проект на розгляд</h4>
            </div>
            <div class="row">
                <form action="newProject" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="title">Назва проекту</label>
                        <input type="text" class="form-control" name="title" id="title" placeholder="Назва від 5 до 64 символів"
                               value="${not empty savedValues.title ? savedValues.title[0] : ''}">
                    </div>
                    <div class="form-group">
                        <label for="prj-img">Зображення</label>
                        <input type="file" name="img" id="prj-img">
                    </div>
                    <div class="form-group">
                        <label for="prj-description">Опис</label>
                        <textarea class="form-control" name="desc" id="prj-description" placeholder="Опис мінімум 15 символів">${not empty savedValues.desc ? savedValues.desc[0] : ''}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="prj-sum">Необхідна сума (₴)</label>
                        <input type="number" class="form-control" name="sum" id="prj-sum" placeholder="5000 ₴"
                               value="${not empty savedValues.sum ? savedValuesr.sum[0] : ''}">
                    </div>
                    <div class="form-group">
                        <label for="prj-date">Дата завершення</label>
                        <input type="date" class="form-control" name="date" id="prj-date" placeholder="Дата у форматі РРРР-ММ-ДД"
                               value="${not empty savedValues.date ? savedValues.date[0] : ''}">
                    </div>
                    <div class="form-group">
                        <label for="category">Категорія</label>
                        <c:set var="categotries" value="<%=Category.values()%>"/>
                        <select id="category" name="category" class="form-control">
                            <c:forEach items="${categotries}" var="category">
                                <option value="${category.name()}" ${not empty savedValues.category && savedValues.category == category.name() ? 'selected' : ''}>${category.ukrainianName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary form-button">Додати</button>
                </form>
            </div>
        </div>
    </div>
    <div id="push"></div>
</div>
<footer>
    <div id="footer">
        <jsp:include page="/partial/footer.jsp"/>
    </div>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>

</html>