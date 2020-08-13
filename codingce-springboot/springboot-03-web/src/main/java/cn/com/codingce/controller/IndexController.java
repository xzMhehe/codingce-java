package cn.com.codingce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//在templates目录下的所有页面, 只能通过controller来跳转
//这个需要模板引擎的支持 thymelaf
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
