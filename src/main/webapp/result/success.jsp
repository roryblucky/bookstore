<%--
  User: RoryGao
  Date: 15/6/19
  Time: 22:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<script>
    window.onload = function () {
        alert("${requestScope.msg}");
        window.location = "${pageContext.request.contextPath}/index.jsp";
    }
</script>
</body>
</html>
