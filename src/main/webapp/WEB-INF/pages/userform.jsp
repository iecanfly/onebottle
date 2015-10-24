<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
<script type="application/javascript" src="<c:url value='/scripts/addressSelect.js'/>"></script>
<body class="bg-black">

<div class="form-box">
    <div class="header"><fmt:message key="userProfile.heading"/></div>
    <form:form commandName="user" method="post" action="userform" id="userForm" autocomplete="off"
               onsubmit="return validateUser(this)">
        <div class="body bg-gray">
            <form:hidden path="id"/>
            <form:hidden path="version"/>
            <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>

            <spring:bind path="user.username">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <appfuse:label styleClass="control-label" key="user.username"/>
                <form:input cssClass="form-control" path="username" id="username"/>
                <form:errors path="username" cssClass="help-block"/>
                <c:if test="${pageContext.request.remoteUser == user.username}">
                <span class="help-block">
                    <a href="<c:url value="/updatePassword" />"><fmt:message key='updatePassword.changePasswordLink'/></a>
                </span>
                </c:if>
            </div>
            <spring:bind path="user.phoneNumber">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}"></spring:bind>
                <appfuse:label styleClass="control-label" key="user.phoneNumber"/>
                <form:input cssClass="form-control" path="phoneNumber" id="phoneNumber"/>
                <form:errors path="phoneNumber" cssClass="help-block"/>
            </div>
            <div class="form-group">
                <appfuse:label styleClass="control-label" key="user.address.province"/>
                <form:select selectedID="${user.address.province}" cssClass="form-control input-sm" path="address.province" id="address_province">
                    <c:forEach var="province" items="${provinceList}">
                        <option value="${province.key}" <c:if test="${user.address.province eq province.key}">selected</c:if>>${province.value}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <appfuse:label styleClass="control-label" key="user.address.city"/>
                <form:select selectedID="${user.address.city}" cssClass="form-control input-sm" path="address.city" id="address_city">
                    <c:forEach var="city" items="${cityList}">
                        <option value="${city.key}" <c:if test="${user.address.city eq city.key}">selected</c:if>>${city.value}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <appfuse:label styleClass="control-label" key="user.address.area"/>
                <form:select selectedID="${user.address.area}" cssClass="form-control input-sm" path="address.area" id="address_area">
                    <c:forEach var="area" items="${areaList}">
                        <option value="${area.key}" <c:if test="${user.address.area eq area.key}">selected</c:if>>${area.value}</option>
                    </c:forEach>
                </form:select>
            </div>

            <spring:bind path="user.address.address">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">  </spring:bind>
                <appfuse:label styleClass="control-label" key="user.address.address"/>
                <form:input cssClass="form-control input-sm" path="address.address" id="address.address"/>
                <form:errors path="address.address" cssClass="help-block"/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                    <fmt:message key="button.save"/>
                </button>

                <c:if test="${param.from == 'list' and param.method != 'Add'}">
                    <button type="submit" class="btn btn-default" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                        <fmt:message key="button.delete"/>
                    </button>
                </c:if>

                <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
                    <fmt:message key="button.cancel"/>
                </button>
            </div>
        </div>
    </form:form>
</div>

<c:set var="scripts" scope="request">
    <script type="text/javascript">
        // This is here so we can exclude the selectAll call when roles is hidden
        function onFormSubmit(theForm) {
            return validateUser(theForm);
        }
    </script>
</c:set>

<v:javascript formName="user" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>

</body>