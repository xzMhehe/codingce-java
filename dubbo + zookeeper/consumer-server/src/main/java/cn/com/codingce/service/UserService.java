package cn.com.codingce.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Reference
    private TicketService ticketService;

    public void buyTicket() {
        System.out.println("===============" + ticketService.getTicket());
    }

}
