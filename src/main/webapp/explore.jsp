<%@ page import="org.rssms.entity.Project" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.04.2016
  Time: 19:32
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
    <jsp:include page="partial/header.jsp"/>
</header>
<div class="content">
    <c:if test="${not empty error}">
        <div class="error"><%= request.getAttribute("error").toString() %>
        </div>
    </c:if>
    <div class="content-title">
        <h2>Список проектів</h2>
        <h4>Проекти, які потребують фінансової допомоги</h4>
    </div>
    <div id="filter-wrapper">
        <div id="filter">
            <div class="sort-by">
                Впорядковувати за:
                <a style="background-color: ${sort == "popularity" ? 'dodgerblue' : 'darkgray'};" href="/explore?sort=popularity&${not empty category ? 'category=' : ''}${category}">популярністью</a>
                <a style="background-color: ${sort == "date" ? 'dodgerblue' : 'darkgray'};" href="/explore?sort=date&${not empty category ? 'category=' : ''}${category}">датою</a>
                <a style="background-color: ${sort == "goal" ? 'dodgerblue' : 'darkgray'};" href="/explore?sort=goal&${not empty category ? 'category=' : ''}${category}">фінальною сумою</a>
            </div>
            <br>
            <div class="category">
                Категорія:
                <c:forEach items="${categories}" var="ctg">
                    <span class="badge filter-badge" style="background-color: ${ctg == category ? ctg.tagColor : 'darkgray'}">
                        <a href="${pageContext.request.contextPath}/explore?category=${ctg}&${not empty sort ? 'sort=' : ''}${sort}">${ctg.ukrainianName}</a>
                    </span>
                </c:forEach>
            </div>
            <a class="reset-filter-button" href="${pageContext.request.contextPath}/explore">Очистити</a>
        </div>
    </div>
    <div id="project-table">
        <c:forEach items="${projects}" var="project">
            <div class="project-card">
                <a href="/projects/${project.projectId}">
                    <div class="img-wrapper-cover">
                        <img class="cover-image" src="data:image/png;base64,${project.avatar}"/>
                    </div>
                </a>
                <div class="description">
                    <a href="/projects/${project.projectId}">
                        <h3>${project.title}</h3>
                        <p class="description-text">
                                ${project.description}
                        </p>
                    </a>
                    <p>
                        <span class='badge' style="background-color: ${project.category.tagColor};"><a
                                href="/categories/${project.category}">${project.category.ukrainianName}</a></span>
                    </p>
                </div>
                <div class="project-footer">
                    <div class="project-author">
                        <a href="/users/${project.creator.userId}">
                            <img class="userpic" src="http://placehold.it/50x50"/>
                        </a>
                        <div class="author-details">
                            <p>
                                <span><a class="username" href="/users/${project.creator.userId}">${project.creator.username}</a></span>
                                <br>
                                <a href="/users/${project.creator.userId}">
                                    <span class="add-user-info">${project.creator.fullName}</span>
                                </a>
                            </p>
                        </div>
                    </div>
                    <div class="progressbar-wrapper">
                        <c:set var="project" scope="request" value="${project}"></c:set>
                        <%
                            Project project = (Project) pageContext.getAttribute("project");
                            double width = ((double) project.getFundedSum() / (double) project.getGoalCost()) * 100;
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
                </div>
                <div class="spacer"></div>
            </div>
        </c:forEach>
    </div>
    <div id="page-navigator">
        <a class="page-item" href="#">1</a>
        <a class="page-item page-item-active" href="#">2</a>
        <a class="page-item" href="#">3</a>
        <span>...</span>
        <a class="page-item" href="#">123</a>
        <a class="page-item" href="#">Далі >></a>
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