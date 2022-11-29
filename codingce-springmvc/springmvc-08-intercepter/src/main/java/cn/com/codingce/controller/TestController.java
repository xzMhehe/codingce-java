package cn.com.codingce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/t1")
    public String test1() {
        System.out.println("TestController" + "执行了");
        return "ok";
    }
}
