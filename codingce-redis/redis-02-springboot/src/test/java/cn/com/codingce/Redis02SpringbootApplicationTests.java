package cn.com.codingce;

import cn.com.codingce.pojo.User;
import cn.com.codingce.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void contextLoads() {
        // 在企业开发中, 我们80%的情况下, 都不会使用原生方式去编写代码
        // RedisUtils

        // redisTemplate
        // opsForValue() 操作字符串 类似 String
        // opsForList() 操作List 类似 List
        // opsForSet()
        // opsForHash()
        // opsForZSet()
        // opsForGeo()
        // opsForHyperLogLog()

        // 除了基本操作, 常用的方法都可以直接 redisTemplate 操作 例如事务的CRUD

        // 获取 redis 连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();


        redisTemplate.opsForValue().set("mykey", "xz");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    void test() throws JsonProcessingException {
        // 真实开发都是使用 json 来传递对象
        User user = new User("全栈自学社区", 2);
        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    void testUtil() {
        redisUtils.set("name", "全栈自学社区");
        System.out.println(redisUtils.get("name"));
    }

}
