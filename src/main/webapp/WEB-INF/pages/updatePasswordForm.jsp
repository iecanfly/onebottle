<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="updatePassword.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>
<body class="bg-black" id="updatePassword">

<div class="form-box">
    <form:form commandName="user" method="post" id="updatePassword" action="updatePassword" autocomplete="off">
        <div class="header"><fmt:message key="updatePassword.heading"/></div>
        <div class="body bg-gray">
            <div class="form-group">
                <label class="control-label"><fmt:message key="user.username"/></label>
                <input type="text" name="username" class="form-control" id="username" value="<c:out value="${username}" escapeXml="true"/>" required>
            </div>

            <c:choose>
                <c:when test="${not empty token}">
                    <input type="hidden" name="token" value="<c:out value="${token}" escapeXml="true" />"/>
                </c:when>
                <c:otherwise>
                    <spring:bind path="user.password">
                    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}"> </spring:bind>
                        <label class="control-label"><fmt:message key="updatePassword.currentPassword.label"/></label>
                        <input type="password" class="form-control" name="currentPassword" id="currentPassword" required autofocus>
                        <form:errors path="password" cssClass="help-block"/>
                    </div>
                </c:otherwise>
            </c:choose>

            <div class="form-group">
                <label class="control-label"><fmt:message key="updatePassword.newPassword.label"/></label>
                <input type="password" class="form-control" name="password" id="password" required>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">
                    <fmt:message key='updatePassword.changePasswordButton'/>
                </button>
            </div>
        </div>
    </form:form>
</div>

</body>