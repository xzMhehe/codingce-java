package cn.com.codingce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCobtroller {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

}
