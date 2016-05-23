<%@ page import="org.rssms.entity.Project" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="row profile-page-header">
            <h2>Мої проекти</h2>
            <h4>Список проектів, які Ви створили</h4>
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
        </div>
        <div class="row profile-page-header">
            <h2>Підтримані проекти</h2>
            <h4>Проекти, на які Ви пожертвували кошти</h4>
            <!--Таблица проектов-->
        </div>
        <div class="row profile-page-header">
            <h2>Коментарі</h2>
        </div>
        <!--Paginator-->
        <!--<div class="row">-->
        <!--<nav id="pages">-->
        <!--<ul class="pagination">-->
        <!--<li>-->
        <!--<a href="#" aria-label="Previous">-->
        <!--<span aria-hidden="true">&laquo;</span>-->
        <!--</a>-->
        <!--</li>-->
        <!--<li><a href="#">1</a></li>-->
        <!--<li><a href="#">2</a></li>-->
        <!--<li><a href="#">3</a></li>-->
        <!--<li><a href="#">4</a></li>-->
        <!--<li><a href="#">5</a></li>-->
        <!--<li>-->
        <!--<a href="#" aria-label="Next">-->
        <!--<span aria-hidden="true">&raquo;</span>-->
        <!--</a>-->
        <!--</li>-->
        <!--</ul>-->
        <!--</nav>-->
        <!--</div>-->
    </div>

    <div id="push"></div>
</div>
<footer>
    <jsp:include page="${pageContext.request.contextPath}/partial/footer.jsp"/>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>

</html>