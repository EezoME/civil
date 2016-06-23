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
    <script src="resources/js/google-analytics-code.js" ></script>
    <title>UITest</title>
</head>

<body>
<div id="wrap">
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
                        <p><span class="category-badge" style="background-color: ${project.category.tagColor}; margin-left: 8px;">
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
                                     aria-valuemax="100" style="width: <%=width%>%;">
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
        <div class="row">
            <nav class="pages">
                <ul class="pagination">
                    <c:if test="${currentPage!=1}">
                    <li>
                        <a href="${pageContext.request.pathInfo}?page=${currentPage-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    </c:if>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li><a href="">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageContext.request.pathInfo}?page=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                    <c:if test="${currentPage lt noOfPages}">
                        <li>
                            <a href="${pageContext.request.pathInfo}?page=${currentPage+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
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