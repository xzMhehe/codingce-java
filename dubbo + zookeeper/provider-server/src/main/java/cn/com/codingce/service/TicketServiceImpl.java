package cn.com.codingce.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

//zooheeper服务注册与发现
@Service
@Component  //使用了dubbo后尽量别用Service注解     使用的话  使用dubbo的service注解
public class TicketServiceImpl implements TicketService {


    @Override
    public String getTicket() {
        return "掌上编程";
    }
}
