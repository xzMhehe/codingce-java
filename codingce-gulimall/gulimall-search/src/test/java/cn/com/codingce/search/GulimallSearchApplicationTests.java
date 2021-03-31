package cn.com.codingce.search;

import cn.com.codingce.search.config.GulimallElasticSearchConfig;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
     * Search API
     *
     * @throws IOException
     */
    @Test
    void searchData() throws IOException {
        // 创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices("bank");

        // 指定 DSL，检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 构造检索条件
//        searchSourceBuilder.query();
//        searchSourceBuilder.from();
//        searchSourceBuilder.size();
//        searchSourceBuilder.aggregations();

        searchSourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));
        System.out.println(searchSourceBuilder.toString() + "searchSourceBuilder.toString()=======");

        // 按照年龄的值分布进行聚合查询
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg")
                .field("age").size(10);

        searchSourceBuilder.aggregation(ageAgg);

        // 计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        searchSourceBuilder.aggregation(balanceAvg);


        searchRequest.source(searchSourceBuilder);

        // 执行检索
        SearchResponse searchResponse = client.search(searchRequest, GulimallElasticSearchConfig.COMMON_OPTIONS);

        // 分析结果

        System.out.println(searchResponse.toString() + "======");

//        Map map = JSON.parseObject(searchResponse.toString(), Map.class);
        // 获取所有查到的数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
//            searchHit.getIndex();
            String source = searchHit.getSourceAsString();
            Accout accout = JSON.parseObject(source, Accout.class);
            System.out.println("accout: " + accout.toString());
        }

        // 获取这次的检索信息
        Aggregations aggregations = searchResponse.getAggregations();
//        for (Aggregation aggregation : aggregations.asList()) {
//            System.out.println("当前聚合： " + aggregation.getName());
//        }

        Terms aggregation = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : aggregation.getBuckets()) {
            System.out.println("年龄" + bucket.getKeyAsString() + "人数" + bucket.getDocCount());
        }

        Aggregation balanceAvg1 =   aggregations.get("balanceAvg");
        System.out.println("平均薪资：" + balanceAvg1);


    }

    /**
     * 测试存储数据
     * 更新也可以
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
    public class Accout {
        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;
    }

    @Data
    @AllArgsConstructor
    public class User {
        private String userName;
        private String gender;
        private Integer age;
    }

}
