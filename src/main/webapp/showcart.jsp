<%--
  User: RoryGao
  Date: 15/6/20
  Time: 17:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>

<table border="1" width="435">
    <tr>
        <th>书籍名称</th>
        <th>作者</th>
        <th>价格</th>
        <th>类型</th>
        <th>描述</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${sessionScope.shopcart}" var="item" varStatus="i">
        <tr class="${i.count % 2 == 0 ? 'odd' : 'even'}">

            <td><c:if test="${item.picturePath != null}">
                <img src="${pageContext.request.contextPath}/upload/${item.picturePath}"/></c:if><br/>
                    ${item.name}
            </td>

            <td>${item.author}</td>
            <td>${item.price}</td>
            <td>${item.category.name}</td>
            <td>${item.description}</td>
            <td><a href="${pageContext.request.contextPath}/book_removeBookFromCart.action?id=${item.id}">删除</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            <button onclick="window.location='${pageContext.request.contextPath}/order_commitBill.action'">结算</button>
        </td>
    </tr>
</table>
</body>
</html>
