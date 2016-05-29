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
            <div class="project-summary">
                <h2>${project.title}</h2>
                <h4>Aвтор:<a href="/users/${project.creator.userId}"> ${project.creator.username}
                    (${project.creator.fullName})</a>
                </h4>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="thumbnail">
                    <img src="data:image/png;base64,${project.avatar}"/>
                    <div class="category-wrapper">
                        <span class="category-badge" style="background-color: ${project.category.tagColor};"><a
                            href="/explore?category=${project.category}">${project.category.ukrainianName}</a></span>
                    </div>
                    <div id="description">
                        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                            <li class="active"><a href="#details" data-toggle="tab">Деталі проекту</a></li>
                            <li><a href="#comments" data-toggle="tab">Коментарі <span class="badge">${fn:length(comments)}</span></a></li>
                            <li><a href="#donations" data-toggle="tab">Кошти <span class="badge">${fn:length(donations)}</span></a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="details">
                                <p>${project.description}</p>
                            </div>
                            <div class="tab-pane" id="comments">
                                <% if (request.getAttribute("comments") == null) { %><div class="alert alert-warning">Немає коментарів</div><%} else {%>
                                    <c:forEach items="${comments}" var="comment">
                                        <div class="comment">

                                            <!--a href="#" class="comment-author-avatar"><img src="data:image/png;base64,></a-->
                                            <span class="author"><a href="/users/${comment.author.userId}">${comment.author.username}</a></span><br/>
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
                                <% } %>
                                <div class="do-comment">
                                    <% if (request.getRemoteUser() == null){%>
                                        <div class="alert alert-danger"><a href="/login"><b>Авторизуйтеся</b></a>, щоб мати можливість залишати коментарі.</div>
                                    <% } else {%>
                                        <form role="form" action="projects/" method="post" class="comment-from">
                                            <input type="hidden" name="projectID" value="${project.projectId}"/>
                                            <h4>Залишити коментар</h4>
                                            Ви: <span class="current-author text-primary">${pageContext.request.remoteUser}</span><br/>
                                            <div class="form-group">
                                                <textarea class="form-control" style="width: 100%; margin-top: 5px;" rows="5" name="content" id="comment" placeholder="Коментар..."></textarea>
                                            </div>
                                            <button type="submit" style="margin: 0" class="btn btn-primary form-button">Коментувати</button>
                                        </form>
                                    <% } %>
                                </div>
                            </div>
                            <div class="tab-pane" id="donations">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Ім'я користувача</th>
                                            <th>Сума</th>
                                            <th>Коментар</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${donations}" var="donation">
                                            <tr>
                                                <td>
                                                    <c:if test="${empty donation.user}">
                                                        Анонім
                                                    </c:if>
                                                    <c:if test="${not empty donation.user}">
                                                        <a href="/users/${donation.user.userId}">${donation.user.username}</a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    ${donation.amount}
                                                </td>
                                                <td>
                                                    ${donation.comment}
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>

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
    </div>

    <div id="push"></div>
</div>
<footer>
    <jsp:include page="partial/footer.jsp"/>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/backfix.js"></script>
</body>

</html>