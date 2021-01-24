package cn.com.codingce.controller;

import cn.com.codingce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mxz
 * @since 2021-01-01
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping({"/index"})
    public String index() {
        return "index";
    }

    @GetMapping({"/", "/toLogin"})
    public String toLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String toRegister() {
        return "register";
    }
}
