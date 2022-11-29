<%--
  Created by IntelliJ IDEA.
  User: xzMa
  Date: 2020/8/8
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--在web-inf下所有的页面或者资源只能通过controller, 或者selvlet来进行访问--%>
<h1>登陆页面</h1>
<form action="${pageContext.request.contextPath}/user/login" method="post">
用户名:<input type="text" name="username">
密码:<input type="password" name="password">
    <input type="submit" value="提交">

</form>
</body>
</html>
