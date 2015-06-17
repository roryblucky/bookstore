<%--
  User: RoryGao
  Date: 15/6/17
  Time: 21:02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<h3 align="left">新用户注册</h3>

<form action="${pageContext.request.contextPath}/user_registerUser.action" method="post">
    <table border="1" width="438">
        <tr>
            <td>(*)用户名：</td>
            <td>
                <input type="text" name="userName"/>
            </td>
        </tr>
        <tr>
            <td>(*)密码：</td>
            <td>
                <input type="password" name="passwd"/>
            </td>
        </tr>
        <tr>
            <td>(*)重复密码：</td>
            <td>
                <input type="password" name="repasswd"/>
            </td>
        </tr>
        <tr>
            <td>(*)手机号：</td>
            <td>
                <input type="text" name="phoneNum"/>
            </td>
        </tr>
        <tr>
            <td>(*)邮箱：</td>
            <td>
                <input type="email" name="email"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="注册"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
