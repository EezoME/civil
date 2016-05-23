<%@ page import="org.rssms.entity.Project" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.04.2016
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <title>UITest</title>
</head>

<body>
<div id="wrap">
    <%--<header id="big-header">--%>
    <%--<div class="container-fluide">--%>
    <%--<nav class="navbar navbar-default">--%>
    <%--<div class="container">--%>
    <%--<div class="navbar-header">--%>
    <%--<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNav">--%>
    <%--<span class="icon-bar"></span>--%>
    <%--<span class="icon-bar"></span>--%>
    <%--<span class="icon-bar"></span>--%>
    <%--</button>--%>
    <%--<a class="navbar-brand" href="#">--%>
    <%--<img alt="sss" width="150" height="30" src="http://placehold.it/150x30"/>--%>
    <%--</a>--%>
    <%--<a href="#">--%>
    <%--<input type="button" class="btn btn-primary navbar-btn" value="Запропонувати проект"/>--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--<div class="collapse navbar-collapse" id="myNav">--%>
    <%--<ul class="nav navbar-nav navbar-right">--%>
    <%--<li><a href="#">Список проектів</a></li>--%>
    <%--<li><a href="#">Вхід</a></li>--%>
    <%--<li><a href="#">Реєстрація</a></li>--%>
    <%--</ul>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</nav>--%>
    <%--<div id="slogan">--%>
    <%--<div class="container">--%>
    <%--<h2>Інформаційна Система Підтримки Громадських Ініціатив</h2>--%>
    <%--<h4>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce hendrerit enim eget sem sodales,--%>
    <%--at semper...</h4>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</header>--%>
    <jsp:include page="partial/header.jsp"/>
    <div class="container">
        <div class="row">
            <h2>Найпопулярніші проекти</h2>
            <h4>Проекти, які зібрали найбільшу кількість коштів</h4>
        </div>
        <div class="row">
            <c:forEach items="${projects}" var="project">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail min-avatar ">
                        <a href="/projects/${project.projectId}">
                            <img src="data:image/png;base64,${project.avatar}"/>
                    </a>
                        <div class="caption cut-caption">
                            <a href="/projects/${project.projectId}">
                                <h3>${project.title}</h3>
                        </a>
                            <a href="/projects/${project.projectId}">
                                <p>${project.description}</p>
                            </a>
                    </div>
                        <br>
                        <p><span class="badge" style="background-color: ${project.category.tagColor}">
                        <a href="/explore?category=${project.category}">${project.category.ukrainianName}</a></span></p>
                        <div class="project-footer">
                            <div class="project-author">
                                <div class="media">
                                    <div class="media-body">
                                        <a href="/users/${project.creator.userId}">
                                            <h4 class="media-heading">${project.creator.username}</h4>
                                        </a>
                                        <a href="/users/${project.creator.userId}">
                                            <p>${project.creator.fullName}</p>
                                        </a>
                                    </div>
                                </div>
                        </div>
                            <div class="progress">
                                <c:set var="project" scope="request" value="${project}"></c:set>
                                <%
                                    Project project = (Project) pageContext.getAttribute("project");
                                    double width = ((double) project.getFundedSum() / (double) project.getGoalCost()) * 100;
                                %>
                                <div class="progress-bar" role="progressbar" aria-valuemin="0"
                                     aria-valuemax="100" style="width: <%=width%>;%">
                                </div>
                            </div>
                            <div class="funds">
                                <div class="funded">${project.fundedSum} ₴</div>
                                <div class="pull-right funded">${project.goalCost} ₴</div>
                            </div>
                            <div class="exp-date">
                                <div class="created-at">
                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${project.expirationDate}"/>
                                </div>
                                <%
                                    long diff = Math.abs(project.getExpirationDate().getTime() - new Date().getTime());
                                    long diffDays = diff / (24 * 60 * 60 * 1000);
                                %>
                                <div class="pull-right">Залишилось: <b><%= diffDays %> днів</b></div>
                            </div>
                    </div>
                </div>
            </div>
            </c:forEach>
            <%--<div class="col-sm-6 col-md-4">--%>
            <%--<div class="thumbnail">--%>
            <%--<img src="img/zoo.jpg" alt="...">--%>
            <%--<div class="caption cut-caption">--%>
            <%--<h3>Thumbnail label</h3>--%>
            <%--<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta--%>
            <%--gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.Cras justo--%>
            <%--odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget--%>
            <%--metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>--%>
            <%--</div>--%>
            <%--<br>--%>
            <%--<p><span class="badge"><a href="#">Інфраструктура</a></span></p>--%>
            <%--<div class="project-footer">--%>
            <%--<div class="project-author">--%>
            <%--<div class="media">--%>
            <%--<div class="media-body">--%>
            <%--<h4 class="media-heading">Media heading</h4>--%>
            <%--<p>....</p>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="progress">--%>
            <%--<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"--%>
            <%--aria-valuemax="100" style="width: 60%;">--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="funds">--%>
            <%--<div class="funded">12 345 ₴</div>--%>
            <%--<div class="pull-right funded">100 000 ₴</div>--%>
            <%--</div>--%>
            <%--<div class="exp-date">--%>
            <%--<div class="created-at">02.02.2016</div>--%>
            <%--<div class="pull-right">Залишилось: <b>365 днів</b></div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm-6 col-md-4">--%>
            <%--<div class="thumbnail">--%>
            <%--<img src="img/zoo.jpg" alt="...">--%>
            <%--<div class="caption cut-caption">--%>
            <%--<h3>Thumbnail label</h3>--%>
            <%--<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta--%>
            <%--gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.Cras justo--%>
            <%--odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget--%>
            <%--metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>--%>
            <%--</div>--%>
            <%--<br>--%>
            <%--<p><span class="badge"><a href="#">Інфраструктура</a></span></p>--%>
            <%--<div class="project-footer">--%>
            <%--<div class="project-author">--%>
            <%--<div class="media">--%>
            <%--<div class="media-body">--%>
            <%--<h4 class="media-heading">Media heading</h4>--%>
            <%--<p>....</p>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="progress">--%>
            <%--<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"--%>
            <%--aria-valuemax="100" style="width: 60%;">--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="funds">--%>
            <%--<div class="funded">12 345 ₴</div>--%>
            <%--<div class="pull-right funded">100 000 ₴</div>--%>
            <%--</div>--%>
            <%--<div class="exp-date">--%>
            <%--<div class="created-at">02.02.2016</div>--%>
            <%--<div class="pull-right">Залишилось: <b>365 днів</b></div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
        </div>
        <div class="row">
            <nav id="pages">
                <ul class="pagination">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
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