<%--
  User: RoryGao
  Date: 15/6/14
  Time: 10:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1" width="435">
    <tr>
        <th>书籍名称</th>
        <th>作者</th>
        <th>价格</th>
        <th>类型</th>
        <th>描述</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.books}" var="item" varStatus="i">
        <tr class="${i.count % 2 == 0 ? 'odd' : 'even'}">
            <td><img src="${pageContext.request.contextPath}/upload/${item.picturePath}"/><br/>${item.name}</td>
            <td>${item.author}</td>
            <td>${item.price}</td>
            <td>${item.category.name}</td>
            <td>${item.description}</td>
            <td>
                <a href="${pageContext.request.contextPath}/book_showBookInfo.action?id=${item.id}">修改</a>
                <a href="${pageContext.request.contextPath}/book_removeBook.action?id=${item.id}">删除</a>
            </td>
        </tr>

    </c:forEach>
    <tr>
        <td colspan="6">
            <input type="button"
                   onclick="javascript:location='${pageContext.request.contextPath}/book_getCategories.action'"
                   value="增加书籍"/>
        </td>
    </tr>
</table>

</body>
</html>
