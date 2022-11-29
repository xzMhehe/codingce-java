package cn.com.codingce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //代表这个类会被Spring接管,  被这个注解的类, 中的所有方法, 如果返回值是Spring, 并且有具体的页面可以跳转, 那么就会被视图解析器解析;
public class ControllerTest2 {

    @RequestMapping("/t2")
    public String test1(Model model) {
        model.addAttribute("msg", "注解实现");
        return "test";  //WEB-INF/jsp/test.jsp
    }

    @RequestMapping("/t3")
    public String test2(Model model) {

        return "a";  //WEB-INF/jsp/test.jsp
    }

}
