<%--
  User: RoryGao
  Date: 15/6/14
  Time: 13:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<form action="${pageContext.request.contextPath}/book_updateBook.action" enctype="multipart/form-data" method="post">
    <input type="hidden" value="${requestScope.book.id}" name="id">
    <table border="1">
        <tr>
            <td>(*)书籍名称:</td>
            <td>
                <input type="text" name="name" value="${requestScope.book.name}"/>
            </td>
        </tr>
        <tr>
            <td>作者:</td>
            <td>
                <input type="text" name="author" value="${requestScope.book.author}"/>
            </td>
        </tr>
        <tr>
            <td>售价:</td>
            <td>
                <input type="text" name="price" value="${requestScope.book.price}"/>
            </td>
        </tr>
        <tr>
            <td>图片:</td>
            <td>
                <input type="file" name="picuture"/>
            </td>
        </tr>
        <tr>
            <td>描述：</td>
            <td>
                <textarea rows="3" cols="38" name="description">${requestScope.book.description}</textarea>
            </td>
        </tr>
        <tr>
            <td>所属分类：</td>
            <td>
                <select name="categoryId">
                    <c:forEach items="${requestScope.categories}" var="category">
                        <c:choose>
                            <c:when test="${requestScope.book.category.id eq category.id}">
                                <option value="${category.id}" selected>${category.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${category.id}">${category.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
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
