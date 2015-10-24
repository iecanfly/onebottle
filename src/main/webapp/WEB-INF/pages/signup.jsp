<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>
<body class="bg-black">
<script type="application/javascript" src="<c:url value='/scripts/addressSelect.js'/>"></script>
<div class="form-box">
    <div class="header"><fmt:message key="signup.heading"/></div>
    <form:form commandName="user" method="post" action="signup" id="signupForm" autocomplete="off"
               onsubmit="return validateSignup(this)">
        <div class="body bg-gray">
            <spring:bind path="user.username">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}"></spring:bind>
                <spring:message code="user.username" var="userNamePlaceHolder"/>
                <form:input cssClass="form-control input-sm" path="username" id="username" autofocus="true"
                            placeholder="${userNamePlaceHolder}*"/>
                <form:errors path="username" cssClass="help-block"/>
            </div>

            <spring:bind path="user.password">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}"></spring:bind>
                <spring:message code="user.password" var="userPasswordPlaceHolder"/>
                <form:password cssClass="form-control input-sm" path="password" id="password" showPassword="false"
                               placeholder="${userPasswordPlaceHolder}*"/>
                <form:errors path="password" cssClass="help-block"/>
            </div>
            <spring:bind path="user.phoneNumber">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}"></spring:bind>

                <spring:message code="user.phoneNumber" var="userPhPlaceHolder"/>
                <form:input cssClass="form-control input-sm" path="phoneNumber" id="phoneNumber" placeholder="${userPhPlaceHolder}*"/>
                <form:errors path="phoneNumber" cssClass="help-block"/>
            </div>
            <spring:bind path="user.isBuyer">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                </spring:bind>
                <label for="isBuyer" class="checkbox">
                    <input id="isBuyer" name="isBuyer" type="checkbox" <c:if test="${user.isBuyer}">checked</c:if>>
                    <fmt:message key="user.isBuyer"/></label>
                <form:errors path="isBuyer" cssClass="help-block"/>
            </div>


            <div class="form-group enteredAddress">
                <label><fmt:message key="user.address.address"/></label><br>
                <span>${user.address.provinceName}&nbsp ${user.address.cityName}&nbsp ${user.address.areaName}&nbsp</span><a id="btnNotCorrectAddress" href="#">不是？</a>
            </div>

            <div class="form-group computedAddress">
                <form:select cssClass="form-control input-sm" path="address.province" id="address_province">
                    <c:forEach var="province" items="${provinceList}">
                        <option value="${province.key}"
                                <c:if test="${user.address.province eq province.key}">selected</c:if>>${province.value}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group computedAddress">
                <form:select cssClass="form-control input-sm" path="address.city" id="address_city">
                    <c:forEach var="city" items="${cityList}">
                        <option value="${city.key}"
                                <c:if test="${user.address.city eq city.key}">selected</c:if>>${city.value}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group computedAddress">
                <form:select cssClass="form-control input-sm" path="address.area" id="address_area">
                    <c:forEach var="area" items="${areaList}">
                        <option value="${area.key}"
                                <c:if test="${user.address.area eq area.key}">selected</c:if>>${area.value}</option>
                    </c:forEach>
                </form:select>
            </div>
            <spring:bind path="user.address.address">
            <div class="form-group enteredAddress${(not empty status.errorMessage) ? ' has-error' : ''}"></spring:bind>
                <spring:message code="user.address.address" var="userAddressHolder"/>
                <form:input cssClass="form-control input-sm" path="address.address" id="address.address" placeholder="${userAddressHolder}*"/>
                <form:errors path="address.address" cssClass="help-block"/>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                    <fmt:message key="button.register"/>
                </button>
                <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
                    <fmt:message key="button.cancel"/>
                </button>
            </div>
        </div>
    </form:form>
</div>
<c:set var="scripts" scope="request">
    <v:javascript formName="signup" staticJavascript="false"/>
    <script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
</c:set>

</body>