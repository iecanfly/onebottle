<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="login.title"/></title>
    <meta name="menu" content="Login"/>
</head>
<body id="login" class="bg-black">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7a47709620e13098e9ac9539b39ddf9a"></script>

<div class="form-box">
    <form method="post" id="loginForm" action="<c:url value='/j_security_check'/>"
          onsubmit="saveUsername(this);return validateForm(this)" autocomplete="off">
        <div class="header"><fmt:message key="login.heading"/></div>
        <div class="body bg-gray">
            <c:if test="${param.error != null}">
            <div class="alert alert-danger alert-dismissable">
                <fmt:message key="errors.password.mismatch"/>
            </div>
            </c:if>
            <div class="form-group">
                <input type="text" name="j_username" id="j_username" class="form-control"
                       placeholder="<fmt:message key="label.username"/>" required tabindex="1">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="j_password" id="j_password" tabindex="2"
                       placeholder="<fmt:message key="label.password"/>" required>
            </div>
            <div class="form-group">
                <c:if test="${appConfig['rememberMeEnabled']}">
                    <label for="rememberMe" class="checkbox">
                        <input type="checkbox" name="_spring_security_remember_me" id="rememberMe" tabindex="3"/>
                        <fmt:message key="login.rememberMe"/></label>
                </c:if>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block" name="login" tabindex="4">
                    <fmt:message key='button.login'/>
                </button>
            </div>


    </form>

    <p>
        <fmt:message key="login.signup">
            <fmt:param><c:url value="/signup"/></fmt:param>
        </fmt:message>
    </p>

    <c:set var="scripts" scope="request">
        <%@ include file="/scripts/login.js" %>
    </c:set>


    <p><fmt:message key="updatePassword.requestRecoveryTokenLink"/></p>
</div>
</div>
<script>
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });

    var x, y;
    window.onload = function () {
        $("#btnSignup").on("click", function () {
            location.href = location.href.replace("login", "signup" + (x && y ? "?location=" + y + "," + x : ""));
            return false;
        });
    };

    $.ajax({
        url: "http://api.map.baidu.com/location/ip?ak=7a47709620e13098e9ac9539b39ddf9a&coor=bd09ll",
        type: "GET",
        dataType: 'jsonp'
    }).done(function (r) {
        if (r & r.content) {
            x = r.content.point.x;
            y = r.content.point.y;
        }
    });
</script>
</body>