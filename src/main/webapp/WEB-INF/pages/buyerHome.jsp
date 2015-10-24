<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
</head>
<script type="application/javascript" src="<c:url value='/scripts/buyerhome.js'/>"></script>
<body class="home">

<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">

    <!-- Main content -->
    <section class="content">
        <div class="row">

            <div class="col-xs-12 col-lg-3">
                <c:if test="${empty order}">
                    <div class="small-box bg-blue" id="divOrder">
                        <div class="inner">
                            <h3>
                                ${favOrder.numBottles}<fmt:message key="item.unit"/>
                            </h3>
                            <p>
                                ${favOrder.seller.username}
                            </p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-ios7-cart-outline"></i>
                        </div>
                        <a id="btnOrder" href="#" class="small-box-footer">
                            <fmt:message key="orders.button.message"/> <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>

                </c:if>
                <c:if test="${not empty order}">
                    <div class="box box-solid bg-light-blue">
                        <div class="box-header">
                            <h3 class="box-title"><fmt:message key="orders.success.header"/></h3>
                        </div>
                        <div class="box-body">
                            <p>
                                <fmt:message key="orders.success.message">
                                    <fmt:param value="${order.buyer}"/>
                                    <fmt:param value="${order.address.cityName}"/>
                                    <fmt:param value="${order.address.areaName}"/>
                                    <fmt:param value="${order.address.address}"/>
                                </fmt:message>
                            </p>
                            <p><fmt:message key="orders.status.title"/> : <fmt:message key="${order.orderStatus.key}"/></p>
                            <p>
                                <c:if test="${order.status eq 1}">
                                    <button id="btnCancelOrder" type="button" class="btn btn-danger" orderid="${order.id}"><fmt:message key="orders.button.cancel"/></button>
                                </c:if>
                                <c:if test="${order.status eq 2}">
                                    <button id="btnForceCancelOrder" type="button" class="btn btn-danger" orderid="${order.id}"><fmt:message key="orders.button.cancel"/></button>
                                </c:if>
                                <c:if test="${order.status eq 3}">
                                    <button id="btnConfirmDelivered" type="button" class="btn btn-success" orderid="${order.id}"><fmt:message key="orders.button.confirm.delivery"/></button>
                                </c:if>
                            </p>
                        </div>
                    </div>

                </c:if>

            </div>

        </div>
    </section>
    <!-- /.content -->
</aside>
<!-- /.right-side -->
</body>
