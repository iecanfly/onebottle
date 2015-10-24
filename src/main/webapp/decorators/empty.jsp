<!DOCTYPE html>

<%@ include file="/common/taglibs.jsp" %>
<html class="bg-black" lang="en">
<head>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<c:url value="/images/favicon.ico"/>"/>
    <t:assets/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles/bootstrap.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles/style.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles/skins/square/blue.css'/>"/>
    <link href="<c:url value='/styles/admin/AdminLTE.css'/>" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="<c:url value='/scripts/iCheck.js'/>"></script>
    <title><decorator:title/> | <fmt:message key="webapp.name"/></title>

    <decorator:head/>
</head>
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
<div id="header" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value='/'/>"><fmt:message key="webapp.name"/></a>
        </div>

        <%@ include file="/common/menu.jsp" %>
        <%--
            <c:if test="${pageContext.request.locale.language ne 'en'}">
            No need for internationalisation for now
        --%>
        <c:if test="${false}">
            <div id="switchLocale"><a href="<c:url value='/?locale=en'/>">
                <fmt:message key="webapp.name"/> in English</a>
            </div>
        </c:if>
    </div>

</div>
<decorator:body/>

<div id="footer" class="bg-black container-fluid">
        <span class="col-sm-6 text-left"><fmt:message key="webapp.version"/>
            <c:if test="${pageContext.request.remoteUser != null}">
                | <fmt:message key="user.status"/> ${pageContext.request.remoteUser}
            </c:if>
        </span>
        <span class="col-sm-6 text-right">
            &copy; <fmt:message key="copyright.year"/> <a href="<fmt:message key="company.url"/>"><fmt:message key="company.name"/></a>
        </span>
</div>
<%= (request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : "" %>
</body>
</html>
