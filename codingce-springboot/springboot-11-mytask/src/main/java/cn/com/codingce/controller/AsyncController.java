package cn.com.codingce.controller;

import cn.com.codingce.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService service;


    @RequestMapping("/hello")
    public String hello() {
        service.hello();
        return "ok";
    }

}
