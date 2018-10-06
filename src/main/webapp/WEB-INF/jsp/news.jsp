<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/header.jspf"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container bg-light">
    <%----%>
    <hr>
        Links and names pages below repeat on all pages, it is for work, will be delete after that.
        After authorization you well to see users indicators too.<br>
    <h6>
        <ul style="display: inline-block">
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=main"/>">Main</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=authors"/>">Authors</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=login"/>">Login</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=admin"/>">Admin</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=registration"/>">Registration</a></li>
        </ul>
    </h6>
        ${sessionScope.user.id}
        ${sessionScope.user.role}
        ${sessionScope.user.status}
    <h3>News page</h3>
        <hr>
    <%--delete after--%>
</div>
</body>
</html>
