package cn.com.codingce.webrtc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController
 *
 * @author ma
 */
@Controller
public class IndexController {

    @RequestMapping("")
    public String indexDefault(Model model) {
        return "index";
    }

}
