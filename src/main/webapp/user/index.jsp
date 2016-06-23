<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.04.2016
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/global.css"/>
    <script src="../resources/js/google-analytics-code.js" ></script>
    <title>Персональній кабінет</title>
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/partial/header-small.jsp"/>
</header>
Hello on simpleuser page!!
<footer>
    <jsp:include page="${pageContext.request.contextPath}/partial/footer.jsp"/>
</footer>
</body>
</html>
