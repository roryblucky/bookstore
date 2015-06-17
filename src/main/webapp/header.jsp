<%--
  User: RoryGao
  Date: 15/6/17
  Time: 20:56
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
    <link href="${pageContext.request.contextPath}/css/app.css" rel="stylesheet" type="text/css">
</head>
<div style="text-align: right;">
    <a href="${pageContext.request.contextPath}">首页</a>
    <c:if test="${sessionScope.customer==null }">
        <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
        <a href="${pageContext.request.contextPath}/register.jsp">注册</a>
    </c:if>
    <c:if test="${sessionScope.customer!=null }">
        欢迎您：${sessionScope.customer.username }
        <a href="${pageContext.request.contextPath}/">注销</a>
        <a href="${pageContext.request.contextPath}/">我的订单</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/showCart.jsp">我的购物车</a>
</div>
<br/><br/>

<div>
    <h1>XX的小书屋</h1>
</div>