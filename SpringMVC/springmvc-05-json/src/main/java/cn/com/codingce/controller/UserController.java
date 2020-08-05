package cn.com.codingce.controller;

import cn.com.codingce.pojo.User;
import cn.com.codingce.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

//@RestController  用这个就不用@ResponseBody
@Controller
public class UserController {

    @RequestMapping(value = "j1", produces = "application/json;charset=utf-8")
    @ResponseBody   //加了这个注解就不会走视图解析器   会直接返回一个字符串
    public String json1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //创建一个对象
        User user = new User("掌上编程", 3, "男");
        String s = mapper.writeValueAsString(user);
        return s;

    }

    //produces:指定响应体返回类型和编码
    @RequestMapping(value = "/json1")
    @ResponseBody
    public String json2() throws JsonProcessingException {
        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        //创建一个对象
        User user = new User("掌上编程", 3, "男");
        User user2 = new User("掌上编程", 3, "男");
        User user3 = new User("掌上编程", 3, "男");
        User user4 = new User("掌上编程", 3, "男");
        User user5 = new User("掌上编程", 3, "男");
        User user6 = new User("掌上编程", 3, "男");

        ArrayList<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        //将我们的对象解析成为json格式
        String str = mapper.writeValueAsString(list);
        return str;
    }

    @GetMapping("/t4")
    public String test4(ModelMap modelMap) {
        modelMap.addAttribute("msg", "ModelMap");
        return "test";
    }

    @ResponseBody
    @RequestMapping("/json5")
    public String json5() throws JsonProcessingException {
        Date date = new Date();
        String json = JsonUtils.getJson(date);
        return json;
    }

}
