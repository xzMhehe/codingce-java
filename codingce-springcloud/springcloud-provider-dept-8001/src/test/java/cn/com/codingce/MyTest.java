package cn.com.codingce;


import cn.com.codingce.service.DeptClientService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest {

    @Autowired
    private DeptClientService deptService;

    @Test
    public void mytest() {
        System.out.println(deptService.queryAll());
    }

}
