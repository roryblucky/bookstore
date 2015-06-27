<%--
  User: RoryGao
  Date: 15/6/27
  Time: 16:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>我的订单</title>
</head>
<body>
<table border="1">
    <tr>
        <th>订单编号</th>
        <th>物品</th>
        <th>价格</th>
        <th>状态</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${requestScope.myOrders}" var="order" varStatus="i">
        <tr class="${i.count % 2 == 0 ? 'odd' : 'even'}">
            <td>${order.id}</td>
            <td>
                <ul>
                    <c:forEach items="${order.orderItems}" var="item">
                        <li>${item.book.name} ---> ${item.book.price}</li>
                    </c:forEach>
                </ul>
            </td>
            <td>${order.totalPrice}</td>
            <td>
                <c:choose>
                    <c:when test="${order.status == 0}">
                        未发货
                    </c:when>
                    <c:when test="${order.status == 1}">
                        已发货
                    </c:when>
                    <c:otherwise>
                        交易成功
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:if test="${order.status == 1}">
                    <input type="button"
                           onclick="location='${pageContext.request.contextPath}/order_confirmGetGoods.action?orderId=${order.id}'"
                           value="确认收货"/>
                </c:if>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
