<%--
  User: RoryGao
  Date: 15/6/17
  Time: 21:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<h1>登录</h1>

<form action="${pageContext.request.contextPath}/user_login.action" method="post">
  <table border="1" width="438">
    <tr>
      <td>(*)用户名：</td>
      <td>
        <input type="text" name="username"/>
      </td>
    </tr>
    <tr>
      <td>(*)密码：</td>
      <td>
        <input type="password" name="password"/>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="登录"/>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
