package cn.com.codingce.search;

import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        System.out.println(client);
    }

    /**
     * 测试存储数据
     */
    @Test
    void indexData() {
        IndexRequest request = new IndexRequest("users");
        request.id("1");
//        request.source("userName", "后端码匠", "age", 18, "gender", "男");

    }

    @Data
    class User {
        private String userName;
        private String gender;
        private Integer age;
    }

}
