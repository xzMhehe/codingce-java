<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册</title>
    <meta name="description" content="">
</head>
<div>
    <h1>注册</h1>
    <form method="post" action="register">
        <div>
            <input type="text" name="uname" required data-msg="请输入用户名" class="input-material">
            <label class="label-material">用户名</label>
        </div>
        <div>
            <input id="login-password" type="password" name="upwd" required data-msg="请输入密码"
            >
            <label for="login-password" class="label-material" type="password">密码</label>
        </div>
        <input type="submit" value="登录">
    </form>
    <a href="#"><br/></a><br><small>还没有账号? </small><a href="register.jsp"
                                                      class="signup">注册</a>
    <span>
        <%
            out.println("你的ip " + request.getRemoteAddr());
        %>
    </span>
</div>

</body>
</html>