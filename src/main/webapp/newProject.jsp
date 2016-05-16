<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.rssms.entity.User" %>
<%@ page import="org.rssms.enums.Category" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.04.2016
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/global.css"/>
    <title>UI Prototype</title>
</head>
<body>
<header>
    <jsp:include page="partial/header.jsp" />
</header>
<div class="content">
    <c:if test="${not empty error}">
        <div class="error"><%= request.getAttribute("error").toString() %>
        </div>
    </c:if>
    <div class="content-title">
        <h2>Додати проект</h2>
        <h4>Помістити новий проект на розгляд</h4>
    </div>
    <div class="new-form">
        <form action="newProject" method="post" accept-charset="utf-8">
            <label for="prj-title">Назва проекту</label><br>
            <input type="text" name="title" id="prj-title" placeholder="Назва" value="${not empty savedValues.title ? savedValues.title[0] : ''}"/><br>

            <label for="prj-img">Зображення</label><br>
            <input type="file" name="img" id="prj-img"/><br>

            <label for="prj-description">Опис проекту</label><br>
            <textarea name="desc" id="prj-description">${not empty savedValues.desc ? savedValues.desc[0] : ''}</textarea><br>

            <label for="prj-sum">Необхідна сума</label><br>
            <input type="text" name="sum" id="prj-sum" placeholder="5 000" value="${not empty savedValues.sum ? savedValuesr.sum[0] : ''}"/><span
                class="larger">&nbsp;&#8372;</span><br>

            <label for="prj-sum">Дата завершення</label><br>
            <input type="date" name="date" id="prj-date" value="${not empty savedValues.date ? savedValues.date[0] : ''}"/><br>

            <label>Категорія</label><br>
            <c:set var="categotries" value="<%=Category.values()%>"/>
            <select name="category">
                <%--<option value=""></option>--%>
                <c:forEach items="${categotries}" var="category">
                    <option value="${category.name()}" ${not empty savedValues.category && savedValues.category == category.name ? 'selected' : ''}>${category.ukrainianName}</option>
                </c:forEach>
            </select><br>
            <input type="submit" value="Додати"/>

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
