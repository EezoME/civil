<%@ page import="org.rssms.entity.Project" %>
<%@ page import="org.rssms.enums.Category" %>
<%@ page import="org.rssms.enums.Role" %>
<%@ page import="org.rssms.enums.Status" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/simple-sidebar.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UITest</title>
</head>

<body>
<div id="wrapper">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="/admin?action=users">
                    Адмін панель
                </a>
            </li>
            <li>
                <a href="/admin?action=users">Користувачі</a>
            </li>
            <li>
                <a href="/admin?action=projects">Проекти</a>
            </li>
        </ul>
    </div>
    <div id="page-content-wrapper">
        <div id="wrap">
            <header id="small-header">
                <div class="container-fluide">
                    <nav class="navbar navbar-default">
                        <div class="container admin-container">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNav">
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="#">
                                    <img alt="sss" width="150" height="30" src="http://placehold.it/150x30"/>
                                </a>
                                <a href="${pageContext.request.contextPath}/newProject">
                                    <input type="button" class="btn btn-primary navbar-btn"
                                           value="Запропонувати проект"/>
                                </a>
                            </div>
                            <div class="collapse navbar-collapse" id="myNav">
                                <ul class="nav navbar-nav navbar-right">
                                    <% if (request.isUserInRole("ADMINISTRATOR")) {%>
                                    <li><a href="${pageContext.request.contextPath}/admin">Панель адміністратора</a>
                                    </li>
                                    <%}%>
                                    <li><a href="${pageContext.request.contextPath}/explore">Список проектів</a></li>
                                    <% if (request.getRemoteUser() != null) {%>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/user/profile"><%= request.getRemoteUser()%>
                                        </a></li>
                                    <li><a href="${pageContext.request.contextPath}/logout">Вихід</a></li>
                                    <%} else {%>
                                    <li><a href="${pageContext.request.contextPath}/login">Вхід</a></li>
                                    <li><a href="${pageContext.request.contextPath}/signup">Реєстрація</a></li>
                                    <%}%>

                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>
            </header>
            <div class="container admin-container">
                <%--Users--%>
                <c:if test='${param.action=="users"}'>
                <div class="row">
                    <table class="table table-bordered">
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
                                    <td><fmt:formatDate value="${user.bDate}"
                                                        pattern="dd-MM-yyyy"></fmt:formatDate></td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td>
                                        <div class="input-group project-control">
                                            <c:set var="roles" value="<%=Role.values()%>"></c:set>
                                            <select class="form-control" name="${user.userId}_role">
                                                <c:forEach items="${roles}" var="role">
                                                    <option value="${role.name()}" ${role.name()==user.role ? 'selected': ''}>${role.ukrainianName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <button type="submit" class="btn btn-primary">Оновити</button>
                                    </td>
                                </form>
                                <td><a href="admin?action=deleteUser&id=${user.userId}">
                                    <button type="button" class="btn btn-danger ">Видалити</button>
                                </a></td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                    </c:if>
                </div>
                <%--Projets---------------------------------------------------------------------------------------------%>
                <c:if test='${param.action=="projects"}'>
                    <c:set var="statuses" value='<%=Status.values()%>'/>
                    <div class="row">
                        <div id="filter-wrapper">
                            <div id="filter">
                                <div class="sort-by">
                                    Статус:
                                    <c:forEach items="${statuses}" var="status">
                                        <div class="badge" style="background-color: green;">
                                            <a href="${pageContext.request.contextPath}/admin?action=projects&status=${status.name()}">${status.ukrainianName}</a>
                                        </div>
                                    </c:forEach>
                                </div>
                                <br>
                                <a class="pull-left" href="${pageContext.request.contextPath}/admin?action=projects">
                                    <button type="button" class="btn btn-danger">Очистити</button>
                                </a>
                                <br>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <c:forEach items="${list}" var="project">
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
                                        <form class="form-horizontal" action="admin" method="get">
                                            <input type="hidden" name="action" value="updateProject">
                                            <input type="hidden" name="id" value="${project.projectId}">
                                            <div class="input-group admin-control">
                                                <div class="form-group">
                                                    <c:set var="statuses" value="<%=Status.values()%>"></c:set>
                                                    <select class="form-control" name="${project.projectId}_status">
                                                        <c:forEach items="${statuses}" var="status">
                                                            <option value="${status.name()}" ${status.name()==project.status ? 'selected': ''}>${status.ukrainianName}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <c:set var="categories" value="<%=Category.values()%>"></c:set>
                                                    <select class="form-control" name="${project.projectId}_category">
                                                        <c:forEach items="${categories}" var="category">
                                                            <option value="${category.name()}" ${category.name()==project.category ? 'selected': ''}>${category.ukrainianName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <button type="submit" class="btn btn-primary" value="Оновити">
                                                        Оновити
                                                    </button>
                                                    <a href="admin?action=deleteProject&id=${project.projectId}">
                                                        <button type="button" class="btn btn-danger pull-right">Видалити
                                                        </button>
                                                    </a>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                            </div>
                        </c:forEach>
                    </div>

                </c:if>
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

        </div>


        <div id="push"></div>

    </div>
    <footer>
        <div id="footer">
            <div class="container admin-container">
                <p class="credit">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce hendrerit enim eget sem
                    sodales, at semper... Інформаційна Система Підтримки Громадських Ініціатив &copy; 2016
                    <br/>
                    <br/> Смирнов Кирило, Стадник Дмитро, Чуріков Дмитро</p>
            </div>
        </div>
    </footer>
</div>


<%--jQuery (necessary for Bootstrap's JavaScript plugins)--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<%--Include all compiled plugins (below), or include individual files as needed--%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>

</html>