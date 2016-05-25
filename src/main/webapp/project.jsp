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
    <jsp:include page="partial/header-small.jsp"/>
    <div class="container">
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
                    <div class="caption">
                        <h3>${project.title}</h3>
                        <p>${project.description}</p>
                    </div>

                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Зібрано ${project.fundedSum} ₴</h4>
                    </div>
                    <div class="panel-body">
                        <%
                            Project project = (Project) request.getAttribute("project");
                            double width = ((double) project.getFundedSum() / (double) project.getGoalCost()) * 100;
                        %>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuemin="0"
                                 aria-valuemax="100" style="width: <%=width%>%;">
                            </div>
                        </div>
                        <div class="funds">
                            <div class="funded"> ${project.fundedSum} ₴</div>
                            <div class="pull-right funded">${project.goalCost} ₴</div>
                        </div>
                        <div class="exp-date">
                            <div class="created-at"><fmt:formatDate pattern="yyyy-MM-dd"
                                                                    value="${project.expirationDate}"/></div>
                            <%
                                long diff = Math.abs(project.getExpirationDate().getTime() - new Date().getTime());
                                long diffDays = diff / (24 * 60 * 60 * 1000);
                            %>
                            <div class="pull-right">Залишилось: <b><%= diffDays %> днів</b></div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4>Підтримати</h4>
                    </div>
                    <div class="panel-body">
                        <div id="liqpay_checkout"></div>
                        <script>
                            window.LiqPayCheckoutCallback = function() {
                                LiqPayCheckout.init({
                                    data: "${liqpay_params['data']}",
                                    signature: "${liqpay_params['signature']}",
                                    embedTo: "#liqpay_checkout",
                                    mode: "embed" // embed || popup
                                }).on("liqpay.callback", function(data){
                                    console.log(data.status);
                                    console.log(data);
                                    $.post("/liqpay_callback", { projectId: "${project.projectId}", orderId: data.liqpay_order_id }).done(function(data) { alert('ok') });
                                }).on("liqpay.ready", function(data){
                                    // ready
                                }).on("liqpay.close", function(data){
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
                    <h3 class="panel-title">Коментарі</h3>
                </div>
                <div class="panel-body">
                    Panel content
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
</body>

</html>