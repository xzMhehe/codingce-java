package cn.com.codingce.controller;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

public class TestController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView();

        //业务代码
        String result = "HelloSpringMVC";

        mv.addObject("msg", result);

        mv.setViewName("test"); //类似于项目一: /WEB-INF/jsp/hello.jsp
        return mv;
    }
}
