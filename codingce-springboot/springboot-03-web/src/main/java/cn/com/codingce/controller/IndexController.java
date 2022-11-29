package cn.com.codingce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

//在templates目录下的所有页面, 只能通过controller来跳转
//这个需要模板引擎的支持 thymelaf
@Controller
public class IndexController {

//    @GetMapping("/index")
//    public String index() {
//        return "index";
//    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @GetMapping("/test2")
    public String test1(Model model){
        //存入数据
        model.addAttribute("msg","Hello,Thymeleaf");
        //classpath:/templates/test.html
        return "test2";
    }

    @GetMapping("/test3")
    public String test3(Model model){
        //存入数据
        model.addAttribute("msg","<h1>Hello,Thymeleaf</h1>");
        //classpath:/templates/test.html

        model.addAttribute("users", Arrays.asList("掌上编程", "xzM", "TianRuan"));
        return "test3";
    }

//    @GetMapping({"/", "/index.html"})
//    public String index() {
//        return "index";
//    }

}
