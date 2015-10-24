<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<tbody>
<tr>
    <th style="width: 10px">#</th>
    <th><fmt:message key="item.name"/></th>
    <th><fmt:message key="item.description"/></th>
    <th><fmt:message key="item.price"/></th>
</tr>
<c:forEach items="${items}" var="item" varStatus="status">
    <tr itemId="${item.id}" class="item">
        <td>${status.index + 1}</td>
        <td>${item.name}</td>
        <td>${item.description}</td>
        <td>${item.price}</td>
    </tr>
</c:forEach>
</tbody>