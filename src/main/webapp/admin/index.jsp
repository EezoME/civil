<%@ page import="org.rssms.entity.Project" %>
<%@ page import="org.rssms.enums.Category" %>
<%@ page import="org.rssms.enums.Role" %>
<%@ page import="org.rssms.enums.Status" %>
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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/global.css"/>
    <%--<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admin_style.css">
    <title>Admin Page</title>
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/partial/header-small.jsp"/>
</header>
<div id="menu">
    <div class="pure-menu">
        <ul class="pure-menu-list">
            <li class="pure-menu-item"><a href="/admin?action=users" class="pure-menu-link">Користувачі</a></li>

            <li class="pure-menu-item" class="pure-menu-link">
                <a href="/admin?action=projects" class="pure-menu-link">Проекти</a>
            </li>
        </ul>
    </div>
</div>
<div class="content">
    <c:if test='${param.action=="users"}'>
        <table class="pure-table">
            <thead>
            <tr>
                <th>id</th>
                <th>ПІБ</th>
                <th>Дата народження</th>
                <th>Username</th>
                <th>Email</th>
                <th>Роль</th>
                <th colspan="2">Дія</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="user">
                <tr>
                    <form action="admin" method="get">
                        <input type="hidden" name="action" value="updateUser">
                        <input type="hidden" name="id" value="${user.userId}">
                        <td>${user.userId}</td>
                        <td>${user.fullName}</td>
                        <td><fmt:formatDate value="${user.bDate}" pattern="dd-MM-yyyy"></fmt:formatDate></td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>
                            <c:set var="roles" value="<%=Role.values()%>"></c:set>
                            <select name="${user.userId}_role">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.name()}" ${role.name()==user.role ? 'selected': ''}>${role.ukrainianName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="Оновити"/>
                        </td>
                    </form>
                    <td>
                        <a href="admin?action=deleteUser&id=${user.userId}"><input type="submit" value="Видалити"></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:if>
    <c:if test='${param.action=="projects"}'>
        <c:set var="statuses" value='<%=Status.values()%>'/>
        <div id="filter-wrapper">
            <div id="filter">
                <div class="status">
                    Статус:
                    <c:forEach items="${statuses}" var="status">
                        <div class="badge filter-badge" style="background-color: green">
                            <a href="${pageContext.request.contextPath}/admin?action=projects&status=${status.name()}">${status.ukrainianName}</a>
                        </div>
                    </c:forEach>
                </div>
                <a class="reset-filter-button"
                   href="${pageContext.request.contextPath}/admin?action=projects">Очистити</a>
            </div>
        </div>
        <div id="project-table">
            <c:forEach items="${list}" var="project">

                <div class="project-card">
                    <a href="/projects/${project.projectId}">
                        <div class="img-wrapper-cover">
                            <img class="cover-image" src="data:image/png;base64,${project.avatar}"/>
                        </div>
                    </a>
                    <div class="description">
                        <a href="/projects/${project.projectId}">
                            <h3>${project.title}</h3>
                            <p class="description-text">${project.description}</p>
                        </a>
                        <p>
                    <span class='badge' style="background-color: ${project.category.tagColor};"><a
                            href="/explore?category=${project.category}">${project.category.ukrainianName}</a></span>
                        </p>
                    </div>
                    <div class="project-footer">
                        <div class="project-author">
                            <a href="/users/${project.creator.userId}">
                                <img class="userpic" src="http://placehold.it/50x50"/>
                            </a>
                            <div class="author-details">
                                <p>
                            <span><a class="username"
                                     href="/users/${project.creator.userId}">${project.creator.username}</a></span>
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
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${project.expirationDate}"/>
                            </div>
                            <%
                                long diff = Math.abs(project.getExpirationDate().getTime() - new Date().getTime());
                                long diffDays = diff / (24 * 60 * 60 * 1000);
                            %>
                            <div class="time-left">Залишилось: <b><%= diffDays %> днів</b></div>
                        </div>
                        <form action="admin" method="get">
                            <input type="hidden" name="action" value="updateProject">
                            <input type="hidden" name="id" value="${project.projectId}">
                            <c:set var="statuses" value="<%=Status.values()%>"></c:set>
                            <select name="${project.projectId}_status">
                                <c:forEach items="${statuses}" var="status">
                                    <option value="${status.name()}" ${status.name()==project.status ? 'selected': ''}>${status.ukrainianName}</option>
                                </c:forEach>
                            </select>
                            <c:set var="categories" value="<%=Category.values()%>"></c:set>
                            <select name="${project.projectId}_category">
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.name()}" ${category.name()==project.category ? 'selected': ''}>${category.ukrainianName}</option>
                                </c:forEach>
                            </select>
                            <input type="submit" value="Оновити">
                        </form>
                        <a href="admin?action=deleteProject&id=${project.projectId}"><input type="button"
                                                                                            value="Видалити"></a>
                    </div>
                    <div class="spacer"></div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<footer>
    <jsp:include page="${pageContext.request.contextPath}/partial/footer.jsp"/>
</footer>
</body>
</html>
