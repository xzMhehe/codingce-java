package cn.com.codingce.springcloud.controller;

import cn.com.codingce.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author xzMa
 *
 * Ribbon 通过微服务名字去访问
 *
 * 通过   RestTemplate    去调用
 */
@RestController
public class DeptConsumerController {

    // 理解消费者, 不应该有service层
    //RestFul风格
    //(url, 实体: Map classs<T> responseType)

    @Autowired
    private RestTemplate restTemplate;  //提供多种便捷访问远程http服务的方法

    //原 private static final String REST_URL_PREFIX = "http://localhost:8001";
    // Ribbon 我们这里是地址   因该是一个变量   通过服务来访问
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";


    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    //http://localhost:8001/dept/list
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class );
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list" , List.class );
    }

}
