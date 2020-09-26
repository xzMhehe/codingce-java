package cn.com.codingce.springcloud.controller;

import cn.com.codingce.springcloud.pojo.Dept;
import cn.com.codingce.springcloud.service.DeptService;
import cn.com.codingce.springcloud.service.DeptServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xzMa
 *
 * 提供RestFul服务
 */
@RestController
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if(dept == null) {
            throw new RuntimeException("id=>" + id + ", 不存在该用户, 或者信息无法找到~");
        }
        return dept;
    }

    /**
     * 备选方法
     * @param id
     * @return
     * 注意该方法上面没有访问地址
     */
    public Dept hystrixGet(@PathVariable("id") Long id) {
        Dept dept = new Dept(id, "id=>" + id + ", 不存在该用户, 或者信息无法找到~", "no datebase");
        return dept;
    }

}