<%--
  User: RoryGao
  Date: 15/6/13
  Time: 23:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../header.jsp" %>
<form method="post" action="${pageContext.request.contextPath}/category_updateCategory.action">
    <input type="hidden" value="${requestScope.category.id}" name="id"/>
    <table border="1">
        <tr>
            <td>(*)分类名称:</td>
            <td>
                <input type="text" name="name" required value="${requestScope.category.name}"/>
            </td>
        </tr>
        <tr>
            <td>分类描述：</td>
            <td>
                <textarea rows="3" cols="38" name="description">${requestScope.category.description}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="保存"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
