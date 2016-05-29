<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <title>UITest</title>
</head>

<body>
<div id="wrap">
    <jsp:include page="partial/header-small.jsp"/>
    <div class="container">
        <c:if test="${not empty error}">
            <div class="error"><%= request.getAttribute("error").toString() %>
            </div>
        </c:if>
        <div class="row">
            <h2>${project.title}</h2>
            <h4>Aвтор:<a href="/users/${project.creator.userId}"> ${project.creator.username}
                (${project.creator.fullName})</a></h4>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="thumbnail">
                    <img src="data:image/png;base64,${project.avatar}"/>
                    <p><span class="badge" style="background-color: ${project.category.tagColor};"><a
                            href="/explore?category=${project.category}">${project.category.ukrainianName}</a></span>
                    </p>
                    <div id="description">
                        <h3>${project.title}</h3>
                        <p>${project.description}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-primary" id="project-funds">
                    <jsp:include page="partial/project-funds.jsp"/>
                </div>

                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4>Підтримати</h4>
                    </div>
                    <div class="panel-body">
                        <div id="liqpay_checkout"></div>
                        <script>
                            window.LiqPayCheckoutCallback = function () {
                                LiqPayCheckout.init({
                                    data: "${liqpay_params['data']}",
                                    signature: "${liqpay_params['signature']}",
                                    embedTo: "#liqpay_checkout",
                                    mode: "embed" // embed || popup
                                }).on("liqpay.callback", function (data) {
                                    console.log(data.status);
                                    console.log(data);
                                    $.post("/liqpay_callback", {
                                        projectId: "${project.projectId}",
                                        orderId: data.order_id
                                    }).done(function (data) {
                                        $("#project-funds").html(data);
                                    });
                                }).on("liqpay.ready", function (data) {
                                    // ready
                                }).on("liqpay.close", function (data) {
                                    // close
                                });
                            };
                        </script>
                        <script src="//static.liqpay.com/libjs/checkout.js" async></script>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <% if (request.getAttribute("comments") == null) { %>Немає коментарів<%} else {%>
                        ${fn:length(comments)} коментарів <% }%>
                    </h3>
                </div>

                <div class="panel-body">
                    <c:forEach items="${comments}" var="comment">
                        <div class="comment">

                            <!--a href="#" class="comment-author-avatar"><img src="data:image/png;base64,></a-->
                            <span class="author">${comment.author.username}</span><br/>
                            <span class="date-posted"><fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${comment.timePosted}"/></span>
                            <c:if test="${comment.author.username == pageContext.request.remoteUser || pageContext.request.isUserInRole(\"ADMINISTRATOR\")}">
                                <a href="${pageContext.request.pathInfo}?delete=${comment.commentId}" class="delete">Видалити</a>
                            </c:if>
                            <br/>

                            <div class="comment-content">
                                    ${comment.content}
                            </div>
                        </div>
                        <hr class="comment-divider">
                    </c:forEach>
                </div>

                <div class="do-comment">
                    <form action="projects/" method="post" class="comment-from">
                        <input type="hidden" name="projectID" value="${project.projectId}"/>
                        <% if (request.getRemoteUser() == null){%>
                        <span class="error"><a href="/login">Авторизуйтеся</a>, щоб мати можливість залишати коментарі.</span>
                        <% } else {%>
                        <h4>Залишити коментар</h4>
                        <span class="author">Ви: ${pageContext.request.remoteUser}</span><br/>
                        <textarea class="comment-post" name="content" id="prj-comment" placeholder="Comment..."></textarea><br/>
                        <button type="submit" class="btn btn-primary form-button comment-post">Коментувати</button>
                        <% } %>
                    </form>
                </div>
            </div>
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
<script src="${pageContext.request.contextPath}/resources/js/backfix.js"></script>
</body>

</html>