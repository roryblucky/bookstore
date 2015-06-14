<%--
  User: RoryGao
  Date: 15/6/13
  Time: 23:03
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1" width="435">
    <tr>
        <th>分类名称</th>
        <th>描述</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.categories}" var="item" varStatus="i">
        <tr class="${i.count % 2 == 0 ? 'odd' : 'even'}">
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td>
                <a href="${pageContext.request.contextPath}/category_showCategoryInfo.action?id=${item.id}">修改</a>
                <a href="${pageContext.request.contextPath}/category_removeCategory.action?id=${item.id}">删除</a>
            </td>
        </tr>

    </c:forEach>
    <tr>
        <td colspan="3">
            <input type="button"
                   onclick="javascript:location='${pageContext.request.contextPath}/system/category/addCategory.jsp'"
                   value="增加分类"/>
        </td>
    </tr>
</table>
</body>
</html>
