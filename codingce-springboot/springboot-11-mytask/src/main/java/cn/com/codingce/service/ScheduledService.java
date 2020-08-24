package cn.com.codingce.service;

import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    //在一个特定的时间执行这样的
    public void hello() {
        System.out.println("Hello, 你被执行了");
    }

}
