package cn.com.codingce.controller;

import cn.com.codingce.pojo.User;
import cn.com.codingce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> queryUserList() {
        return userService.queryUserList();
    }

}
