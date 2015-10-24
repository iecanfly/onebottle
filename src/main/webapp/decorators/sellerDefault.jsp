<!DOCTYPE html>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="webapp.name"/> | <fmt:message key="dashboard"/></title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="<c:url value='styles/admin/bootstrap.min.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='styles/admin/font-awesome.min.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='styles/admin/ionicons.min.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='styles/admin/morris/morris.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='styles/admin/jvectormap/jquery-jvectormap-1.2.2.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='styles/admin/fullcalendar/fullcalendar.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='styles/admin/daterangepicker/daterangepicker-bs3.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='styles/admin/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='styles/admin/AdminLTE.css'/>" rel="stylesheet" type="text/css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <script src="http://lib.sinaapp.com/js/jquery/2.0.3/jquery-2.0.3.min.js"></script>
    <script src="scripts/admin/bootstrap.min.js" type="text/javascript"></script>
    <script src="scripts/admin/AdminLTE/app.js" type="text/javascript"></script>
    <script>
        var baseURL = "${pageContext.request.contextPath}";
    </script>
</head>
<header class="header">
    <a href="<c:url value="/"/>" class="logo">
        <!-- Add the class icon to your logo image or logo icon to add the margining -->
        <fmt:message key="webapp.name"/>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="glyphicon glyphicon-user"></i>
                        <span>${pageContext.request.remoteUser} <i class="caret"></i></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header bg-light-blue">
                            <img src="<c:url value='images/admin/avatar3.png'/>" class="img-circle" alt="User Image"/>

                            <p>
                                ${pageContext.request.remoteUser} - <fmt:message key="user.seller"/>
                                <small>Member since Nov. 2014</small>
                            </p>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="<c:url value="userform"/>" class="btn btn-default btn-flat"><fmt:message key="button.edit"/></a>
                            </div>

                            <div class="pull-right">
                                <a href="<c:url value="logout"/>" class="btn btn-default btn-flat"><fmt:message key="user.logout"/></a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
<body class="skin-black">
<div class="wrapper row-offcanvas row-offcanvas-left">
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="<c:url value='/images/admin/avatar3.png'/>" class="img-circle" alt="User Image"/>
                </div>
                <div class="pull-left info">
                    <p><fmt:message key="hello"/>, ${pageContext.request.remoteUser}</p>

                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li>
                    <a href="<c:url value="/sellerHome"/>">
                        <i class="fa fa-bar-chart-o"></i>
                        <span>订单</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/sellerItems"/>">
                        <i class="fa fa-list-ul"></i> <span><fmt:message key="items"/> </span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <decorator:body/>



</body>

</html>
