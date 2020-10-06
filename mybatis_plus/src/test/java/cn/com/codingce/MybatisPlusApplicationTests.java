package cn.com.codingce;

import cn.com.codingce.mapper.UserMapper;
import cn.com.codingce.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /*
    wrapper条件构造器, 这里我们先不用 null
    //查询全部用户
     */
    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setName("掌上编程");
        user.setAge(19);
        user.setEmail("2460798168@qq.com");
        userMapper.insert(user);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setEmail("21211@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

}
