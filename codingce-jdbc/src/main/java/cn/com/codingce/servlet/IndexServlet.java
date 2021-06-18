package cn.com.codingce.servlet;

import cn.com.codingce.entity.Users;
import cn.com.codingce.service.UsersService;
import cn.com.codingce.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private UsersService usersService = new UsersServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> users = null;

        String searchName = request.getParameter("searchName");
        if (searchName == null || "".equals(searchName)) {
            users = usersService.queryAllUsers();
        } else {
            request.getSession().removeAttribute("userList");
            users = usersService.queryAllUsersByName(searchName);
        }
        request.getSession().setAttribute("userList", users);

        users.forEach(e -> System.out.println(e.toString()));

        request.getSession().setAttribute("stuCount", users.size());


        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
