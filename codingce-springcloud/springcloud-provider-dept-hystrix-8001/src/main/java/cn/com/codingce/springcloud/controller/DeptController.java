package cn.com.codingce.springcloud.controller;

import cn.com.codingce.pojo.Dept;
import cn.com.codingce.service.DeptClientService;
import cn.com.codingce.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if(dept == null) {
            throw new RuntimeException("id=>" + id + ", 不存在该用户, 或者信息无法找到~");
        }
        return dept;
    }

    @GetMapping("/dept/get/{id}")
    public Dept hystrixGet(@PathVariable("id") Long id) {
        Dept dept = new Dept(id, "id=>" + id + ", 不存在该用户, 或者信息无法找到~", "no datebase");
        return dept;
    }

}
