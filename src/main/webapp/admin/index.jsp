<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.04.2016
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/global.css"/>
    <title>Admin Page</title>
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/partial/header-small.jsp" />
</header>
TODO: manage users, projects; MODERATOR role
Admin page admins page!!!
<footer>
    <jsp:include page="${pageContext.request.contextPath}/partial/footer.jsp"/>
</footer>
</body>
</html>
