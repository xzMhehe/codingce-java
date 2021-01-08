package cn.com.codincge;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class RedisApplicationTests {

    @Test
    void TestPing() {
        //1 new Jedis 对象即可
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //jedis 所有的命令就是他的基本命令, 就是对象的方法
        System.out.println(jedis.ping());;
    }

    @Test
    void TestKey() {

    }

}
