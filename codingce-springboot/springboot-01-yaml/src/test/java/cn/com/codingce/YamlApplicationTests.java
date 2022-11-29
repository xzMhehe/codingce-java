package cn.com.codingce;

import cn.com.codingce.pojo.Dog;
import cn.com.codingce.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YamlApplicationTests {

    @Autowired //将狗狗自动注入进来
    private Dog dog;

    @Autowired
    private Person person;

    @Test
    public void contextLoads() {
        System.out.println(dog); //打印看下狗狗对象
//        System.out.println(person); //打印看下person对象
    }

}
