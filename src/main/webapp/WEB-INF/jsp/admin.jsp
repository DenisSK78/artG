<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/header.jspf"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<<<<<<< HEAD
<div class="container bg-light">
    <%----%>
        <hr>
        Links and names pages below repeat on all pages, it is for work, will be delete after that.
        After authorization you well to see users indicators too.<br>
    <h6>
        <ul style="display: inline-block">
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=main"/>">Main</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=authors"/>">Authors</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=news"/>">News</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=login"/>">Login</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=registration"/>">Registration</a></li>
        </ul>
    </h6>
    ${sessionScope.user.id}
    ${sessionScope.user.role}
    ${sessionScope.user.status}
    <h3>Admin page</h3>
        <hr>
    <%--delete after--%>
    <div>





    </div>
=======
<div class="container">
    <hr>
    <div>
        <h3>Admin page</h3>
    </div>
    <hr>
<div id="admin-main-div">
        <div class="row">
            <div id="menu-adm">
            </div>
            <div class="col-2">
                <div class="list-group">
                    <div class="list-group-item list-group-item-action disabled adm-vertical-menu">МЕНЮ</div>
                    <a href="<c:url value='/gallery?do=admin&get=users'/>" class="list-group-item list-group-item-action adm-vertical-menu">Пользователи</a>
                    <a href="<c:url value='/gallery?do=admin&get=partners'/>" class="list-group-item list-group-item-action adm-vertical-menu">Партнёры</a>
                    <a href="<c:url value='/gallery?do=admin&get=art_objects'/>" class="list-group-item list-group-item-action adm-vertical-menu">Aрт-объекты</a>
                    <a href="<c:url value='/gallery?do=admin&get=authors'/>" class="list-group-item list-group-item-action adm-vertical-menu">Авторы</a>
                    <a href="<c:url value='/gallery?do=admin&get=materials'/>" class="list-group-item list-group-item-action adm-vertical-menu">Материал</a>
                    <a href="<c:url value='/gallery?do=admin&get=subcontractors'/>" class="list-group-item list-group-item-action adm-vertical-menu">Виды работ</a>
                    <a href="<c:url value='/gallery?do=admin&get=forms_art'/>" class="list-group-item list-group-item-action adm-vertical-menu">Вид искусства</a>
                </div>
            </div>
            <div class="col-10">
                <c:if test="${requestScope.get('fragment') eq 'users'}" >
                    <%@include file="/WEB-INF/jspf/admin/users.jspf"%>
                </c:if>
                <c:if test="${requestScope.get('fragment') eq 'partners'}" >
                    <%@include file="/WEB-INF/jspf/admin/partners.jspf"%>
                </c:if>
                <c:if test="${requestScope.get('fragment') eq 'art_objects'}" >
                    <%@include file="/WEB-INF/jspf/admin/art-object.jspf"%>
                </c:if>
                <c:if test="${requestScope.get('fragment') eq 'authors'}" >
                    <%@include file="/WEB-INF/jspf/admin/authors.jspf"%>
                </c:if>
                <c:if test="${requestScope.get('fragment') eq 'materials'}" >
                    <%@include file="/WEB-INF/jspf/admin/material.jspf"%>
                </c:if>
                <c:if test="${requestScope.get('fragment') eq 'subcontractors'}" >
                    <%@include file="/WEB-INF/jspf/admin/subcontractors.jspf"%>
                </c:if>
                <c:if test="${requestScope.get('fragment') eq 'forms_art'}" >
                    <%@include file="/WEB-INF/jspf/admin/form-art.jspf"%>
                </c:if>
            </div>
        </div>
</div>
>>>>>>> Admin
</div>
</body>
</html>
