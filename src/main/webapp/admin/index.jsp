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
    <script src="../resources/js/google-analytics-code.js" ></script>
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
            <jsp:include page="/partial/header-small.jsp"/>
            <div class="container admin-container">
                <c:if test="${not empty error}">
                    <div class="error"><%= request.getAttribute("error").toString() %>
                    </div>
                </c:if>
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
                    </div>
                    <div class="row">
                        <nav class="pages">
                            <ul class="pagination">
                                <c:if test="${currentPage!=1}">
                                    <li>
                                        <a href="${pageContext.request.pathInfo}?page=${currentPage-1}&action=users"
                                           aria-label="Previous">
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
                                            <li>
                                                <a href="${pageContext.request.pathInfo}?page=${i}&action=users">${i}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>
                                <c:if test="${currentPage lt noOfPages}">
                                    <li>
                                        <a href="${pageContext.request.pathInfo}?page=${currentPage+1}&action=users"
                                           aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
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
                                        <a href="${pageContext.request.contextPath}/admin?action=projects&status=${status}">${status.ukrainianName}</a>
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
                <div class="row">
                    <nav class="pages">
                        <ul class="pagination">
                            <c:if test="${currentPage!=1}">
                                <li>
                                    <a href="${pageContext.request.pathInfo}?page=${currentPage-1}&action=projects&${not empty status ? 'status=' : ''}${status}"
                                       aria-label="Previous">
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
                                        <li>
                                            <a href="${pageContext.request.pathInfo}?page=${i}&action=projects&${not empty status ? 'status=' : ''}${status}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                            <c:if test="${currentPage lt noOfPages}">
                                <li>
                                    <a href="${pageContext.request.pathInfo}?page=${currentPage+1}&action=projects&${not empty status ? 'status=' : ''}${status}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </c:if>

        </div>
        <div id="push"></div>
        <footer>
            <jsp:include page="/partial/footer.jsp"/>
        </footer>
    </div>
</div>

</div>


<%--jQuery (necessary for Bootstrap's JavaScript plugins)--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<%--Include all compiled plugins (below), or include individual files as needed--%>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>

</html>