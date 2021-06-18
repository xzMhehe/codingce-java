<%--
  Created by IntelliJ IDEA.
  User: williamma
  Date: 2021/6/18
  Time: 7:47 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>主页</title>
</head>
<body>
<p>
    今天的日期是: <%= (new java.util.Date()).toString()%>
</p>
<p>
    你的账号: ${teaname}
</p>
<div>学生数量${stuCount}</div>

<div>
    <form action="/index" method="get">
        <input type="text" name="searchName">
        <input type="submit" value="查询">
        <table>
            <thead>
            <tr>
                <td>用户编号</td>
                <td>学号</td>
                <td>姓名</td>
                <td>年龄</td>
                <td>地址</td>
                <td>密码</td>
                <td>生日</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.pid}</td>
                    <td>${user.sno}</td>
                    <td>${user.sname}</td>
                    <td>${user.sage}</td>
                    <td>${user.saddress}</td>
                    <td>${user.spwd}</td>
                    <td>${user.sbirth}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>


</div>
</body>
</html>
