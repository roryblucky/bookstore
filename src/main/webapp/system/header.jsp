<%--
  User: RoryGao
  Date: 15/6/13
  Time: 23:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>后台管理</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<h1>Welcome!</h1>
<a href="${pageContext.request.contextPath}/category_showAllCategories.action">分类管理</a>
<a href="${pageContext.request.contextPath}/book_showAllBooks.action?flag=admin">书籍管理</a>
<a href="${pageContext.request.contextPath}/order_showAllOrders.action">订单管理</a>
<br><br>
