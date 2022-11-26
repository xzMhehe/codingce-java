package cn.com.codingce.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class User {
    @Value("掌上编程")
    public String name;

    @Value("掌上编程")
    public void setName(String name) {
        this.name = name;
    }
}
