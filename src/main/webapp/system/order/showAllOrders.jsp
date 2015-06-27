<%--
  User: RoryGao
  Date: 15/6/27
  Time: 16:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1" width="435">
    <tr>
        <th>订单编号</th>
        <th>物品</th>
        <th>总价格</th>
        <th>状态</th>
        <th>客户姓名</th>
        <th>客户电话</th>
        <th>收货地址</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.pageBean.records}" var="item" varStatus="i">
        <tr class="${i.count % 2 == 0 ? 'odd' : 'even'}">
            <td>${i.index + 1}</td>
            <td>
                <ul>
                    <c:forEach items="${item.orderItems}" var="orderItem">
                        <li>${orderItem.book.name} ---> ${orderItem.book.price}</li>
                    </c:forEach>
                </ul>
            </td>
            <td>${item.totalPrice}</td>
            <td>
                <c:choose>
                    <c:when test="${item.status == 0}">
                        未发货
                    </c:when>
                    <c:when test="${item.status == 1}">
                        已发货
                    </c:when>
                    <c:otherwise>
                        交易成功
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${item.customer.name}</td>
            <td>${item.customer.phoneNum}</td>
            <td>${item.customer.address}</td>
            <td>
                <c:if test="${item.status == 0}">
                    <input type="button"
                           onclick="location='${pageContext.request.contextPath}/order_deliverGoods.action?orderId=${item.id}'"
                           value="发货"/>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8">
            第${pageBean.currentPage}页&nbsp;&nbsp;共${pageBean.totalPages}页&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/order_showAllOrders.action?flag=admin&pageNum=${pageBean.currentPage - 1 > 0 ? pageBean.currentPage - 1 : 1}">上一页</a>
            <a href="${pageContext.request.contextPath}/order_showAllOrders.action?flag=admin&pageNum=${pageBean.currentPage + 1 > pageBean.totalPages? pageBean.totalPages : pageBean.currentPage + 1}">下一页</a>
        </td>
    </tr>
</table>

</body>
</html>