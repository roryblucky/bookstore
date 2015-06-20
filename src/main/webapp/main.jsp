<%--
  User: RoryGao
  Date: 15/6/20
  Time: 16:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title></title>
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
    <c:forEach items="${requestScope.pageBean.records}" var="item" varStatus="i">
        <tr class="${i.count % 2 == 0 ? 'odd' : 'even'}">

            <td><c:if test="${item.picturePath != null}">
                <img src="${pageContext.request.contextPath}/upload/${item.picturePath}"/></c:if><br/>
                    ${item.name}
            </td>

            <td>${item.author}</td>
            <td>${item.price}</td>
            <td>${item.category.name}</td>
            <td>${item.description}</td>
            <td>
                <a href="${pageContext.request.contextPath}/book_add2Cart.action?id=${item.id}">添加到购物车</a>
            </td>
        </tr>

    </c:forEach>
    <tr>
        <td colspan="6">
            第${pageBean.currentPage}页&nbsp;&nbsp;共${pageBean.totalPages}页&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/book_showAllBooks.action?pageNum=${pageBean.currentPage - 1 > 0 ? pageBean.currentPage - 1 : 1}">上一页</a>
            <a href="${pageContext.request.contextPath}/book_showAllBooks.action?pageNum=${pageBean.currentPage + 1 > pageBean.totalPages? pageBean.totalPages : pageBean.currentPage + 1}">下一页</a>
        </td>
    </tr>
</table>
</body>
</html>
