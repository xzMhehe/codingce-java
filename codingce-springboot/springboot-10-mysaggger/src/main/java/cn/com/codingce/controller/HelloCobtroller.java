package cn.com.codingce.controller;

import cn.com.codingce.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Operation接口
@ApiOperation("Hello控制类")
@RestController
public class HelloCobtroller {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/myuser")
    public User user() {
        return new User();
    }

    //Operation接口, 不是放在类上, 是方法
    @ApiOperation("Hello控制类")
    @RequestMapping(value = "/hello2")
    public String hello2() {
        return "hello";
    }

    @ApiOperation("Hello控制类")
    @RequestMapping(value = "/hello3")
    public String hello3(@ApiParam("用户名") String username) {
        return "hello" + username;
    }

}
