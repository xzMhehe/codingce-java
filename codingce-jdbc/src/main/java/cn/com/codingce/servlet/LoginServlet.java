package cn.com.codingce.servlet;


import cn.com.codingce.entity.Teacher;
import cn.com.codingce.service.TeacherService;
import cn.com.codingce.service.impl.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
 * 控制器层：接受view请求   并发给Model
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherService itea = new TeacherServiceImpl();
        //处理登录

        request.setCharacterEncoding("utf-8");
        String teaname = request.getParameter("uname");
        String password = request.getParameter("upwd");
        Teacher loin = new Teacher();
        loin.setTname(teaname);
        loin.setTpwd(password);

        // 调用模型层的登录功能
        int result = itea.login(loin);

        if (result > 0) {
            request.getSession().setAttribute("teaname", teaname);
            response.sendRedirect("/index");
        } else {
            response.sendRedirect("login.jsp");
        }


    }


}
