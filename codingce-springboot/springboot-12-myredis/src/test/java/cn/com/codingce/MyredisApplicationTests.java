package cn.com.codingce;

import cn.com.codingce.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class MyredisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        //redisTemplate
        //opsForValue   操作字符串 类似String
        //opsForList   操作List 类似List
        //opsForSet
        //opsForHash
        //opsForGeo
        //opsForHyperLogLog

        //除了基本操作    我们常用的方法都可以直接redisTemplate操作， 比如事务基本的CRUD

        //获取redis的连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();

        redisTemplate.opsForValue().set("mykey", "掌上编程");
        redisTemplate.opsForValue().get("mykey");
        System.out.println(redisTemplate.opsForValue().get("mykey"));

    }

    @Test
    public void test() throws JsonProcessingException {
        //真实开发一般使用json来传递对象
        User user = new User("掌上编程", 12);

        String jsonUser = new ObjectMapper().writeValueAsString(user);

        redisTemplate.opsForValue().set("user", jsonUser);
        System.out.println(redisTemplate.opsForValue().get("user"));;
    }

}
