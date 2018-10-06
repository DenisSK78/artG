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
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=login"/>">Login</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=authors"/>">Authors</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=news"/>">News</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=news"/>">News</a></li>
            <li style="display: inline-block; margin: 4px"><a href="<c:url value="/gallery?do=admin"/>">Admin</a></li>
        </ul>
    </h6>
    ${sessionScope.user.id}
    ${sessionScope.user.role}
    ${sessionScope.user.status}
    <hr>
    <%--delete after--%>
<div>
    <h3><fmt:message key="page.name.registration"/></h3>
    <form  action="<c:url value="/gallery?do=user_registration"/>" method="post" class="needs-validation" novalidate>

        <div class="form-row-m">
            <div class="col">
                <label for="valid-name"><fmt:message key="all.name"/></label>
                <input type="text" name="name" class="form-control" pattern='(.){2,25}' id="valid-name" placeholder="<fmt:message key="all.name"/>" value="" required>
                <%--<div class="valid-feedback">--%>
                    <%--OK--%>
                <%--</div>--%>
            </div>
        </div>

        <div class="form-row-m" >
            <div class="col">
                <label for="valid-surname"><fmt:message key="all.surname"/></label>
                <input type="text" name="surname" class="form-control" pattern='(.){2,25}' id="valid-surname" placeholder="<fmt:message key="all.surname"/>" value="" required>
            </div>
        </div>

        <div class="form-row-m">
            <div class="col">
                <label for="valid-e_male"><fmt:message key="all.e_male"/></label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="input_e_male">@</span>
                    </div>
                    <input type="email" class="form-control" id="valid-e_male" name="email"
                           pattern='^([A-Za-z0-9_\-.+])+@([A-Za-z0-9_\-.])+\.([A-Za-z]{2,})$'
                           placeholder="<fmt:message key="all.e_male"/>" aria-describedby="input_e_male" required>
                    <div class="invalid-feedback">
                        <fmt:message key="registr.message.valid_email"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-row-m">
            <div class="col">
                <label for="valid-password"><fmt:message key="all.password"/></label>
                <input type="password" pattern='(.){6,25}' class="form-control"  id="valid-password" name="password" placeholder="<fmt:message key="all.password"/>" value="" required>
                <div class="invalid-feedback">
                    <fmt:message key="registr.message.valid_password"/>
                </div>
            </div>
        </div>

        <div class="form-row-m">
            <div class="col">
                <input type="password" class="form-control" id="valid-password-s" placeholder="<fmt:message key="all.ph.repeat_password"/>" value="" required>
            </div>
        </div>

        <div class="form-row-m">
            <div class="col">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="gender" id="radio-male" value="male" checked>
                    <label class="form-check-label" for="radio-male">
                        <fmt:message key="all.male"/>
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="gender" id="radio-female" value="female">
                    <label class="form-check-label" for="radio-female">
                        <fmt:message key="all.female"/>
                    </label>
                </div>
            </div>
        </div>

        <div class="form-row-m" style="overflow: hidden">
            <div class="col" style="white-space:nowrap">
                <div style="margin-left: auto; margin-right: 0; width: 150px">
                    <a class="btn btn-sm btn-outline-secondary but_rules" href="<c:url value="/gallery?do=rules"/>"><fmt:message key="registr.link_rules"/></a>
                </div>
                <div class="form-check" style="width: 200px; ">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                    <label class="form-check-label" for="invalidCheck">
                            <fmt:message key="registr.rules"/>
                    </label>
                    <div class="invalid-feedback">
                            <fmt:message key="registr.message.agree_rules"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-row-m">
            <div class="col">
                <button class="btn btn-outline-success but_reg" type="submit"><fmt:message key="all.send"/></button>
            </div>
        </div>

    </form>
</div>
</div>
</body>
<script><%@include file="/WEB-INF/js/boot-html-valid.js"%></script>
</html>
