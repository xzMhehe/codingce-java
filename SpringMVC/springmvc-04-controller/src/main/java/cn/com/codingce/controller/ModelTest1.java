package cn.com.codingce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ModelTest1 {

    @RequestMapping("m1/t1")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        return "/hello";
    }

    //无视图解析器
    @Controller
    public class ResultSpringMVC {
        @RequestMapping("/rsm/t1")
        public String test1(){
            //转发
            return "/index.jsp";
        }

        @RequestMapping("/rsm/t2")
        public String test2(){
            //转发二
            return "forward:/index.jsp";
        }

        @RequestMapping("/rsm/t3")
        public String test3(){
            //重定向
            return "redirect:/index.jsp";
        }
    }



    //以下都是有视图解析器
    //转发
    @RequestMapping("m1/t2")
    public String test2(Model model) {
        model.addAttribute("msg", "ModelTest1");
        return "test";
    }

    //重定向
    @RequestMapping("m1/t3")
    public String test3(Model model) {
        model.addAttribute("msg", "ModelTest1");
        return "redirect:/index.jsp";
    }

}
