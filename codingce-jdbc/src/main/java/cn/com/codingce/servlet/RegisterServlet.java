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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherService itea = new TeacherServiceImpl();
        //处理登录

        request.setCharacterEncoding("utf-8");
        String teaname = request.getParameter("uname");
        String password = request.getParameter("upwd");
        Teacher loin = new Teacher();
        loin.setTname(teaname);
        loin.setTpwd(password);

        teacherService.addTeacher(loin);
        // 去登陆页面
        response.sendRedirect("login.jsp");
    }
}
