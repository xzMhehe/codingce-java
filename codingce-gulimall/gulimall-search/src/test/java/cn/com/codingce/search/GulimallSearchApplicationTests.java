package cn.com.codingce.search;

import cn.com.codingce.search.config.GulimallElasticSearchConfig;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GulimallSearchApplicationTests {

    @Qualifier("esRestClient")
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
    void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
//        indexRequest.source("userName", "后端码匠", "age", 18, "gender", "男");
        User user = new User("后端码匠", "男", 19);
        String jsonString = JSON.toJSONString(user);


        indexRequest.source(jsonString, XContentType.JSON);

        // 执行操作
        IndexResponse index = client.index(indexRequest, GulimallElasticSearchConfig.COMMON_OPTIONS);

        // 提取有用响应的数据
        System.out.println(index);

    }

    @Data
    @AllArgsConstructor
    class User {
        private String userName;
        private String gender;
        private Integer age;
    }

}
