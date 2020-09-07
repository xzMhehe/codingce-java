package cn.com.codingce;


import cn.com.codingce.service.DeptService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest {

    @Autowired
    private DeptService deptService;

    @Test
    public void mytest() {
        System.out.println(deptService.queryAll());
    }

}
