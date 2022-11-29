package cn.com.codingce.controller;

import cn.com.codingce.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {

    //localhost:8080/user/t1?name=xxx
    @GetMapping("/t1")
    public String test1(String name, Model model) {
        //接收前端参数
        System.out.println("接受前端参数为" + name);
        //将返回的结果传递给前端  Model
        model.addAttribute("msg", name);

        //视图跳转
        return "test";
    }

    //@RequestParam
    @GetMapping("/t2")
    public String test2(@RequestParam("username") String name, Model model) {
        //接收前端参数
        System.out.println("接受前端参数为" + name);
        //将返回的结果传递给前端  Model
        model.addAttribute("msg", name);

        //视图跳转
        return "test";
    }

    /**
     * 1.接收前端用户传递的参数, 判断参数的名字, 假设名字直接在方法上, 可以直接使用
     * 2.假设传递的是一个对象, 陪陪User对象中的字段名, 如果名字一致ok, 否则匹配不到
     * @param user
     * @return
     */
    @GetMapping("/t3")
    public String test3(User user) {
        //接收前端参数
        System.out.println("接受前端参数为" + user.getName() + user.getPwd());
        //将返回的结果传递给前端  Model

        //视图跳转
        return "test";
    }

    @GetMapping("/t4")
    public String test4(ModelMap modelMap) {
        modelMap.addAttribute("msg", "ModelMap");
        return "test";
    }

}
