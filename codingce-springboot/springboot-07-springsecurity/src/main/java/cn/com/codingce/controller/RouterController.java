package cn.com.codingce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/tologin")
    public String toLogin() {
        return "views/login";
    }

    @RequestMapping("/leve1/{id}")
    public String leve1(@PathVariable int id) {
        return "views/login/" + id;
    }

    @RequestMapping("/leve2/{id}")
    public String leve2(@PathVariable int id) {
        return "views/login/" + id;
    }

    @RequestMapping("/leve3/{id}")
    public String leve3(@PathVariable int id) {
        return "views/login/" + id;
    }

}
