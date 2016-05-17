<%@ page import="org.rssms.entity.Project" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: WRKSPACE2
  Date: 5/15/2016
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/global.css"/>
    <title>UI Prototype</title>
</head>
<body>
<header>
    <jsp:include page="partial/header-small.jsp"/>
</header>
<div class="content">
    <div class="content-title">
        <h2>${project.title}</h2>
        <h4>Автор: <a style="color: #999; font-weight: bold;" href="/users/${project.creator.userId}">${project.creator.username} (${project.creator.fullName})</a></h4>
    </div>
    <div class="prj-big-descr">
        <div class="prj-img-large">
            <div class="img-wrapper">
                <img class="project-image" src="data:image/png;base64,${project.avatar}"/>
            </div>
        </div>
        <div class="prj-info">
            <div class="project-footer" style="border-radius: 4px;">
                <h1>${project.fundedSum} &#8372;</h1>
                <h2 style="line-height: 1px;">Зібрано</h2>
                <div class="progressbar-wrapper">
                    <%
                        Project project = (Project) request.getAttribute("project");
                        int width = Math.round(project.getFundedSum() / project.getGoalCost()) * 100;
                    %>
                    <div class="progressbar" style="width: <%= width %>%"></div>
                </div>
                <div class="funds">
                    <div class="funded">${project.fundedSum} &#8372;</div>
                    <div class="target">${project.goalCost} &#8372;</div>
                </div>
                <div class="exp-date">
                    <div class="created-at">
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${project.expirationDate}" />
                    </div>
                    <%
                        long diff = Math.abs(project.getExpirationDate().getTime() - new Date().getTime());
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                    %>
                    <div class="time-left">Залишилось: <b><%= diffDays %> днів</b></div>
                </div>
                <div class="support-wrapper">
                    <a class="support-project" href="#">Підтримати</a>
                </div>
            </div>
        </div>
        <p>
            <span class='badge' style="background-color: ${project.category.tagColor};"><a
                    href="/explore?category=${project.category}">${project.category.ukrainianName}</a></span>
        </p>
        <div class="large-description">
            <h3 style="line-height: 1px;">Деталі (тут еще будут вкладки с комментами, репортами и т.д.)</h3>
            <p style="text-indent: 10px;">
                ${project.description}
            </p>
        </div>
    </div>
</div>
<footer>
    <jsp:include page="${pageContext.request.contextPath}/partial/footer.jsp"/>
</footer>
</body>
</html>
