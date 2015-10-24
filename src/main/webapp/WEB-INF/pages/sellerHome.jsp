<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="home.title"/></title>
</head>
<script type="application/javascript" src="<c:url value='/scripts/sellerHome.js'/>"></script>

<body>
    <!-- Right side column. Contains the navbar and content of the page -->
    <aside class="right-side">

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <section class="col-lg-12 connectedSortable">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title"><fmt:message key="table.order.confirmed.header"/></h3>

                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <th><fmt:message key="table.order.buyer.header"/></th>
                                    <th><fmt:message key="table.order.numbottle.header"/></th>
                                    <th><fmt:message key="table.order.status.header"/></th>
                                    <th><fmt:message key="table.order.address.header"/></th>
                                    <th></th>
                                </tr>
                                <c:forEach items="${takenOrCancelledOrderList}" var="order">
                                    <tr class="${order.orderStatus.type}">
                                        <td>${order.buyer}</td>
                                        <td>${order.numBottle}桶</td>
                                        <td><span class="label label-${order.orderStatus.type}"><fmt:message key="${order.orderStatus.key}"/></span></td>
                                        <td>${order.address.cityName},${order.address.areaName},${order.address.address}</td>
                                        <td>
                                            <c:if test="${order.status eq 2}">
                                                <button type="button" class="btn btn-primary markDelivered btn-sm"
                                                        orderid="${order.id}">
                                                    <fmt:message key="orders.button.markas.delivered"/></button>
                                            </c:if>
                                            <c:if test="${order.status eq 5 or order.status eq 6}">
                                                <button type="button" class="btn btn-primary deleteOrder btn-sm"
                                                        orderid="${order.id}">
                                                    <fmt:message key="orders.button.delete"/></button>
                                            </c:if>

                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                </section>

            </div>

            <div class="row">
                <!-- Left col -->
                <section class="col-lg-12 connectedSortable">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title"><fmt:message key="table.order.header"/></h3>

                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <th><fmt:message key="table.order.buyer.header"/></th>
                                    <th><fmt:message key="table.order.numbottle.header"/></th>
                                    <th><fmt:message key="table.order.address.header"/></th>
                                    <th><fmt:message key="table.order.deliver.header"/></th>
                                </tr>
                                <c:forEach items="${orderList}" var="order">
                                    <tr>
                                        <td>${order.buyer}</td>
                                        <td>${order.numBottle}桶</td>
                                        <td>${order.address.cityName},${order.address.areaName},${order.address.address}</td>
                                        <td>
                                            <button type="button" class="btn btn-primary takeorder btn-sm btn-flat" orderid="${order.id}">&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="table.order.deliver.header"/>&nbsp;&nbsp;&nbsp;&nbsp;</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                </section>
            </div>
        </section>
        <!-- /.content -->
    </aside>
    <!-- /.right-side -->
</div>
</body>
