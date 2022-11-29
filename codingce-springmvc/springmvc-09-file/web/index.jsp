<%--
  Created by IntelliJ IDEA.
  User: xzMa
  Date: 2020/8/8
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
    <input type="file" name="file"/>
    <input type="submit">

  </form>
  <a href="${pageContext.request.contextPath}/download">点击下载</a>
  </body>
</html>
