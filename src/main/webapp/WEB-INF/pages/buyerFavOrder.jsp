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
        <c:if test="${empty favOrder}">
            <div class="row">
                <div class="col-xs-12">
                    <div class="alert alert-info alert-dismissable">
                        <i class="fa fa-info"></i>
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <b><fmt:message key="icon.information"/> </b> <fmt:message key="buyer.fav.notset"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-xs-12">
                    <div class="box box-info">
                        <div class="box-header">
                            <h3 class="box-title"><fmt:message key="table.fav.create"/></h3>
                        </div>
                        <div class="box-body">
                            <div class="form-group">
                                <label><fmt:message key="table.fav.numbottle"/> </label>
                                <select id="numBottleSelect" class="form-control">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                </select>
                            </div>
                        </div>
                        <div id="favSellerDiv" class="box-body">
                            <label><fmt:message key="table.seller.select"/> </label>
                            <table page="1" id="favTable" class="table table-bordered">
                                <tbody>
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th><fmt:message key="table.seller.id"/></th>
                                    <th><fmt:message key="table.seller.phone"/></th>
                                    <th><fmt:message key="table.seller.address"/></th>
                                </tr>
                                <c:set var="MAX_PAGE_SIZE" value="5"/>
                                <c:forEach items="${sellers}" var="seller" varStatus="status">
                                    <tr <c:if test="${status.index ge MAX_PAGE_SIZE}">style="display:none"</c:if> class="seller" sellerId="${seller.id}">
                                        <td>${status.index + 1}</td>
                                        <td>${seller.username}</td>
                                        <td>${seller.phoneNumber}</td>
                                        <td>${seller.address.address}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>


                        </div>

                        <div class="box-body">
                            <ul class="pagination pagination-sm no-margin pull-right">
                                <li><button id="btnSellerPrev" class="btn btn-sm btn-flat prev disabled" >«</button></li>
                                <li><button id="btnSellerNext" class="btn btn-sm btn-flat next <c:if test="${fn:length(sellers) lt 6}"> disabled</c:if>" >»</button></li>
                            </ul>
                        </div>
                        <div id="favItemDiv" class="box-body">
                            <label><fmt:message key="table.seller.select"/> </label>
                            <table id="itemTable" class="table table-bordered">
                                <tbody>
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th><fmt:message key="item.name"/></th>
                                    <th><fmt:message key="item.description"/></th>
                                    <th><fmt:message key="item.price"/></th>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="box-footer">
                            <button id="btnSubmitFav" class="btn btn-primary btn-sm"><fmt:message key="button.save"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty favOrder}">
            <div class="col-xs-12 col-md-4">
                <div class="box box-solid bg-blue">
                    <div class="box-header">
                        <h3 class="box-title"><fmt:message key="nav.buyer.fav"/></h3>
                    </div>
                    <div class="box-body">
                        <p>
                            <fmt:message key="user.seller"/> : ${favOrder.seller.username}
                        </p>
                        <p>
                            <fmt:message key="orders.message"/> : ${favOrder.numBottles} <fmt:message key="item.unit"/>
                        </p>
                        <p>
                            <button id="btnDeleteFav" favId="${favOrder.id}" class="btn btn-danger"><fmt:message key="button.delete"/></button>
                        </p>
                    </div>
                </div>
            </div>
        </c:if>


    </section>
    <!-- /.content -->
</aside>
<!-- /.right-side -->
</body>
