package cn.com.codingce.springcloud.service;

import cn.com.codingce.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author xzMa
 *
 * feign（面向接口编程）    练习  在实体类项目 创建service包  并创建 DeptClientService接口
 *
 *
 * 调用微服务两种方式 1 微服务名字 ribbon   2 接口和注解 feign
 *
 *
 * 只需要 接口和注解
 *
 * 注解
 */
@Component
//@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")  原   下面配合服务降级写法
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    /**
     * 接口
     * @param id
     * @return
     */
    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id);

    /**
     * @return List<Dept>
     */
    @GetMapping("/dept/list")
    public List<Dept> queryAll();

    /**
     * @param dept
     * @return boolean
     */
    @PostMapping("/dept/add")
    public boolean addDept(Dept dept);

}
