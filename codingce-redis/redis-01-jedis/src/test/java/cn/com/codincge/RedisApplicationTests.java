package cn.com.codincge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {
    @Test
    void TestPing() {
        //1 new Jedis 对象即可
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //jedis 所有的命令就是他的基本命令, 就是对象的方法
        System.out.println(jedis.ping());
    }

    @Test
    void TestKey() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("清空数据" + jedis.flushDB());
        System.out.println("判断某个键是否存在: " + jedis.exists("username"));
        System.out.println("新增'<username, mxz>的键值对'" + jedis.set("username", "mxz"));
        System.out.println("新增'<password, mxz>的键值对'" + jedis.set("password", "mxz"));
        System.out.println("系统中所有的键值对如下: ");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        System.out.println("删除键password: " + jedis.del("password"));
        System.out.println("判断键password是否存在: " + jedis.exists("password"));
        System.out.println("查看键username所存储值的类型: " + jedis.type("username"));
        System.out.println("随机返回key空间的一个: " + jedis.randomKey());
        System.out.println("重命名key: " + jedis.rename("username", "name"));
        System.out.println("按索引查询: " + jedis.select(0));
        System.out.println("删除当前选择数据库的所有的key: " + jedis.flushDB());
        System.out.println("返回当前数据库中key的数目: " + jedis.dbSize());
        System.out.println("删除所有数据库中的所有的key: " + jedis.flushAll());
    }

    @Test
    void TestString() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("=====增加数据=====");
        System.out.println(jedis.set("key1", "value1"));
        System.out.println(jedis.set("key2", "value2"));
        System.out.println(jedis.set("key3", "value3"));
        System.out.println("删除键key2" + jedis.del("key2"));
        System.out.println("获取键key2" + jedis.get("key2"));
        System.out.println("修改key1" + jedis.set("key1", "valueChanged"));
        System.out.println("获取key1的值" + jedis.get("key1"));
        System.out.println("在key3后面加入值" + jedis.append("key3", "end"));
        System.out.println("key3的值" + jedis.get("key3"));
        System.out.println("增加多个键值对: " + jedis.mset("key01", "value01", "key02", "value02"));
        System.out.println("获取多个键值对: " + jedis.mget("key01", "key02", "key03"));
        System.out.println("获取多个键值对: " + jedis.mget("key01", "key02", "key03", "key04"));
        System.out.println("删除多个键值对: " + jedis.del("key01", "key02"));
        System.out.println("获取多个键值对: " + jedis.mget("key01", "key02", "key03"));

        jedis.flushDB();
        System.out.println("=====新增键值对防止覆盖原先值=====");
        System.out.println(jedis.setnx("key1", "value1"));
        System.out.println(jedis.setnx("key2", "value2"));
        System.out.println(jedis.setnx("key2", "value2-new"));
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.get("key2"));

        System.out.println("=====新增键值对并设置有效时间=====");
        System.out.println(jedis.setex("key3", 2, "value3"));
        System.out.println(jedis.get("key3"));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.get("key3"));


        System.out.println("=====获取原值, 更新为新值=====");
        System.out.println(jedis.getSet("key2", "key2GetSet"));
        System.out.println(jedis.get("key2"));

        System.out.println("获得key2的值字符串: " + jedis.getrange("key2", 2, 4));
    }

    @Test
    void TestList() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        System.out.println("=====添加一个List=====");
        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
        jedis.lpush("collections", "HashSet");
        jedis.lpush("collections", "TreeSet");
        jedis.lpush("collections", "TreeMap");
        System.out.println("collections的内容" + jedis.lrange("collections", 0, -1));// -1代表倒数
        System.out.println("collections区间 0-3 的元素: " + jedis.lrange("collections", 2, 3));

        System.out.println("===========================================");
        // 删除列表指定的值, 第二个参数为删除的个数(有重复时), 后add进去的值先被删, 类似于出栈
        System.out.println("删除指定元素个数: " + jedis.lrem("collectionws", 2, "HashMap"));
        System.out.println("collections的内容: " + jedis.lrange("collections", 0, -1));
        System.out.println("删除下表0-3区间之外的元素: " + jedis.ltrim("collections", 0, 3));
        System.out.println("collections内容: " + jedis.lrange("collections", 0, -1));
        System.out.println("collections列表出栈(左端): " + jedis.lpop("collections"));
        System.out.println("collections的内容: " + jedis.lrange("collections", 0, -1));
        System.out.println("collections添加元素, 从列表右端, 与lpush相对应: " + jedis.rpush("collections", "test"));
        System.out.println("collections的内容: " + jedis.lrange("collections", 0, -1));
        System.out.println("collections列表出栈(右端): " + jedis.rpop("collections"));
        System.out.println("collections的内容: " + jedis.lrange("collections", 0, -1));
        System.out.println("collections指定下标 1 的内容: " + jedis.lset("collections", 1, "FLinkedHashMap"));
        System.out.println("collections的内容: " + jedis.lrange("collections", 0, -1));

        System.out.println("===========================================");
        System.out.println("collections的长度: " + jedis.llen("collections"));
        System.out.println("获取collections下标为 2 的元素" + jedis.lindex("collections", 2));
        System.out.println("===========================================");
        jedis.lpush("sortedList", "3", "6", "2", "4", "5", "7", "9");
        System.out.println("sortedList排序前: " + jedis.lrange("sortedList", 0, -1));
        System.out.println(jedis.sort("sortedList"));
        System.out.println("sortedList排序后：" + jedis.lrange("sortedList", 0, -1));
    }

}
