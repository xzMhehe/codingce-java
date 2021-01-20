package cn.com.codingce;

import cn.com.codingce.pojo.User;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CodingceEsApiApplicationTests {

    public static final String ES_INDEX = "codingce_index";

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Test
    void contextLoads() throws IOException {
        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest("codingce_index");
        // 执行创建请求, IndicesClient, 请求后获得响应
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    //测试获取索引
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("codingce_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);

    }


    //测试删除索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("codingce_index");
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    //测试添加文档
    @Test
    void testAddDocument() throws IOException {
        User u = new User("全栈自学社区", 3);
        // 创建请求
        IndexRequest request = new IndexRequest("codingce_index");

        // 规则 put codingce_index/_doc/1
        request.id("1");
        // 1s
        request.timeout(TimeValue.timeValueSeconds(1));

        // 将我们的数据放入请求   json
        IndexRequest source = request.source(JSON.toJSONString(u), XContentType.JSON);

        // 客户端发送请求
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);

        System.out.println(index.toString());
        System.out.println(index.status()); // 成功就是 CREATE
    }

    // 获取文档 先判断是否存在
    @Test
    void testIsExists() throws IOException {
        GetRequest request = new GetRequest("codingce_index", "1");
        // 不获取返回的_source 的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");


        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 判断完 存在则获取文档信息
    @Test
    void testGetDocument() throws IOException {
        GetRequest request = new GetRequest("codingce_index", "1");

        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        System.out.println(response);
    }

    @Test
    void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("codingce_index", "1");
        updateRequest.timeout(TimeValue.timeValueSeconds(1));
        User u = new User("全栈自学社区", 4);
        updateRequest.doc(JSON.toJSONString(u), XContentType.JSON);


        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse);
        System.out.println(updateResponse.status());
    }

    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("codingce_index", "1");
        deleteRequest.timeout(TimeValue.timeValueSeconds(1));

        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.toString());
        System.out.println(deleteResponse.status());
    }


    //批量插入
    @Test
    void testAddDocuments() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(10));

        List<User> list = new ArrayList<>();
        list.add(new User("全栈自学社区1", 1));
        list.add(new User("全栈自学社区2", 2));
        list.add(new User("全栈自学社区3", 3));
        list.add(new User("全栈自学社区4", 4));
        list.add(new User("全栈自学社区5", 5));
        list.add(new User("全栈自学社区6", 6));
        list.add(new User("全栈自学社区7", 7));
        list.add(new User("全栈自学社区8", 8));

        for (int i = 0; i < list.size(); i++) {
            // 批量更新和批量删除, 就在这里修改对应的请求就可以了
            bulkRequest.add(new IndexRequest("codingce_index")
                    .id("" + (i + 1))
                    .source(JSON.toJSONString(list.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.toString());
        System.out.println(bulkResponse.hasFailures());
    }

    // 批量查询
    // SearchRequest    搜索请求
    // SearchSourceBuilder  条件构造
    @Test
    void testSearchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest(ES_INDEX);

        // 查询条件 可以使用 QueryBuilders 工具类
        // termQuery 精确匹配
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "全栈自学社区1");
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();


        // 构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        searchSourceBuilder.query(matchAllQueryBuilder);
//        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(60));
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.from();
        searchSourceBuilder.size();

        searchRequest.source(searchSourceBuilder);


        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }

    }
}
