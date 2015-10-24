<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
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