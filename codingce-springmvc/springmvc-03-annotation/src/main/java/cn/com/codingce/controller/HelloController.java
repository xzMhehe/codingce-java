package cn.com.codingce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController{

    @RequestMapping("/h1")
    public String hello(Model mv) {
        //封装数据
        mv.addAttribute("msg", "HelloAnnotation");
        return "hello"; //会被视图解析器处理
    }


}
