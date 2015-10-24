<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="home.title"/></title>
</head>
<body>
    <!-- Right side column. Contains the navbar and content of the page -->
    <aside class="right-side">
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-sm-6">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title"><fmt:message key="items"/></h3>
                        </div><!-- /.box-header -->
                        <div class="box-body no-padding">
                            <table class="table table-condensed">
                                <tbody><tr>
                                    <th style="width: 10px">#</th>
                                    <th><fmt:message key="item.name"/> </th>
                                    <th><fmt:message key="item.description"/></th>
                                    <th style="width: 40px"><fmt:message key="item.price"/></th>
                                    <th style="width: 40px"><fmt:message key="button.delete"/></th>
                                </tr>
                                <c:forEach items="${items}" var="item" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${item.name}</td>
                                        <td>${item.description}</td>
                                        <td>${item.price}</td>
                                        <td><a href="<c:url value="/deleteItems/${item.id}"/>" class="btn btn-primary btn-xs"><fmt:message key="button.delete"/></a></td>
                                    </tr>
                                </c:forEach>
                                </tbody></table>
                        </div><!-- /.box-body -->
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title"><fmt:message key="items.new"/> </h3>
                        </div><!-- /.box-header -->
                        <div id="itemPriceAlert" class="row center-block" style="display: none">
                            <div class="alert alert-danger col-xs-10 col-xs-offset-2">
                                <a href="#" class="alert-link"><fmt:message key="item.price.invalid"/> </a>
                            </div>
                        </div>
                        <!-- form start -->
                        <form:form modelAttribute="item" role="form"  method="post" autocomplete="off">
                            <div class="box-body">
                                <div class="form-group">
                                    <form:errors path="name" element="div" cssClass="text-danger"/>
                                    <label for="txtItemName"><fmt:message key="item.name"/></label>
                                    <input type="text" name="name" value="${item.name}" class="form-control" id="txtItemName" placeholder="<fmt:message key="item.name.placeholder"/>">
                                </div>
                                <div class="form-group">
                                    <label for="txtItemDescription"><fmt:message key="item.description"/></label>
                                    <input type="text" name="description" value="${item.description}" class="form-control" id="txtItemDescription" placeholder="<fmt:message key="item.description.placeholder"/>">
                                </div>
                                <div class="form-group">
                                    <form:errors path="price" element="div" cssClass="text-danger"/>
                                    <label for="txtItemPrice"><fmt:message key="item.price"/></label>
                                    <div class="input-group">
                                        <input type="text" name="price" value="${item.price}" class="form-control text-right" id="txtItemPrice" placeholder="<fmt:message key="item.price.placeholder"/>">
                                        <span class="input-group-addon">.00</span>
                                    </div>

                                </div>
                            </div><!-- /.box-body -->

                            <div class="box-footer">
                                <button id="btnSubmitItem" type="submit" class="btn btn-primary"><fmt:message key="button.save"/> </button>
                            </div>
                        </form:form>
                    </div>
                </div>

            </div>
        </section>
        <!-- /.content -->
    </aside>
    <!-- /.right-side -->
</div>
</body>
