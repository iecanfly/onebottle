<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
</head>
<body class="home">

<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Main content -->
    <section class="content">
        <div class="col-sm-6">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><fmt:message key="history"/></h3>
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                    <table class="table table-condensed">
                        <tbody><tr>
                            <th><fmt:message key="user.seller"/> </th>
                            <th><fmt:message key="item.name"/></th>
                            <th><fmt:message key="item.price"/></th>
                            <th><fmt:message key="item.unit"/></th>
                            <th><fmt:message key="orders.status.title"/></th>
                            <th><fmt:message key="orders.date.title"/></th>
                        </tr>
                        <c:forEach items="${history}" var="orderItem" varStatus="status">
                            <tr>
                                <td>${orderItem.seller}</td>
                                <td>${orderItem.item.name}</td>
                                <td>${orderItem.item.price}</td>
                                <td>${orderItem.numBottle}</td>
                                <td><fmt:message key="${orderItem.orderStatus.key}"/></td>
                                <td><fmt:formatDate value="${orderItem.orderDateTime}" pattern="yyyy-MM-dd"/> </td>
                            </tr>
                        </c:forEach>
                        </tbody></table>
                </div><!-- /.box-body -->
            </div>
        </div>
    </section>
    <!-- /.content -->
</aside>
<!-- /.right-side -->
</body>
