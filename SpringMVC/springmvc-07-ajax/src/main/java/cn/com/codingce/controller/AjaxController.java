package cn.com.codingce.controller;

import cn.com.codingce.pojo.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @RequestMapping("/t1")
    public String test1(Model model) {
        model.addAttribute("msg", "hello");
        return "hello";
    }

    @RequestMapping("a1")
    public void a1(String name, HttpServletResponse response) throws IOException {
        System.out.println("a1:param=>" + name);
        if ("掌上".equals(name)) {
            response.getWriter().print(true);
        } else {
            response.getWriter().print(false);
        }
    }

    @RequestMapping("/a2")
    public List<User> a2() {
        ArrayList<User> list = new ArrayList<User>();
        //添加数据
        list.add(new User("掌上编程", 1, "男"));
        list.add(new User("掌上编程", 2, "男"));
        list.add(new User("掌上编程", 3, "男"));
        list.add(new User("掌上编程", 4, "男"));
        list.add(new User("掌上编程", 5, "男"));
        return list;
    }

}
