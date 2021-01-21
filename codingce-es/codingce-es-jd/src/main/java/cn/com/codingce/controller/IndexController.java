package cn.com.codingce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mxz
 */
@Controller
public class IndexController {


    @GetMapping({"/index"})
    public String index() {
        return "index";
    }

    @GetMapping({"/", "/myindex"})
    public String myindex() {
        return "myindex";
    }

}
