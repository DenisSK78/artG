
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
            After authorization you well to see users indicators too.
            <h6>
                <ul style="display: inline-block">
                    <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=main"/>">Main</a></li>
                    <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=authors"/>">Authors</a></li>
                    <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=news"/>">News</a></li>
                    <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=admin"/>">Admin</a></li>
                    <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=registration"/>">Registration</a></li>
                </ul>
            </h6>
        ${sessionScope.user.id}
        ${sessionScope.user.role}
        ${sessionScope.user.status}
            <hr>
        <%--delete after--%>

        <%--<c:if test="${sessionScope.get('message') eq 'not_found'}" >--%>
            <%--<div class="alert alert-info" id="my-alert"><fmt:message key="message.user.not_found"/>--%>
                <%--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>--%>
            <%--</div>--%>
        <%--</c:if>--%>
        <form role="form" action="<c:url value="/gallery?do=enter"/>" method="post" class="needs-validation" novalidate>
            <div class="form-row-m">
                <label for="input_email"><fmt:message key="all.e_male"/></label>
                <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroupPrepend">@</span>
                </div>
                    <input type="email" class="form-control" id="input_email"
                           pattern='^([A-Za-z0-9_\-.+])+@([A-Za-z0-9_\-.])+\.([A-Za-z]{2,})$'
                           placeholder="<fmt:message key="all.e_male"/>" name="email" autocomplete="off" value="" required>
                    <div class="invalid-feedback">
                        <fmt:message key="registr.message.valid_email"/>
                    </div>
                </div>
            </div>
            <div class="form-row-m">
                <label for="input_password"><fmt:message key="all.password"/></label>
                <%--pattern='(.){6,25}'--%>
                <input type="password" class="form-control" id="input_password" placeholder="<fmt:message key="all.password"/>" name="password" autocomplete="off" value="">
                <div class="invalid-feedback">
                    <fmt:message key="registr.message.valid_password"/>
                </div>
            </div>
            <div class="form-row-m">
                <div class="checkbox">
                    <label><input type="checkbox"> <fmt:message key="login.save_check_box"/></label>
                </div>
            </div>
            <div class="container col-5">
                <button type="submit" class="btn btn-outline-success but_login_sign_in"><fmt:message key="login.but_submit"/></button>
                <a href="<c:url value="/gallery?do=registration"/>" class="btn btn-outline-success but_from_reg" role="button" aria-pressed="true"><fmt:message key="login.but_registration"/></a>
            </div>
        </form>
    </div>
</body>
<script><%@include file="/WEB-INF/js/boot-html-valid.js"%></script>
</html>
