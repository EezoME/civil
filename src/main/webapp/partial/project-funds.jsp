<%@ page import="org.rssms.entity.Project" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: WRKSPACE2
  Date: 5/25/2016
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="funded">${project.fundedSum} ₴</div>
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
