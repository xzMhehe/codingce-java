package cn.com.codingce.service;

import cn.com.codingce.pojo.Content;
import cn.com.codingce.utils.HtmlParseUtil;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 业务逻辑层
 *
 * @author mxz
 */
@Service
public class ContentService {

    public static final String ES_INDEX = "jd_goods";

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    /**
     * 1 解析数据 放入 es 中
     *
     * @param keywords
     * @return
     * @throws Exception
     */
    public Boolean parseContent(String keywords) throws Exception {
        List<Content> contents = new HtmlParseUtil().parseJD(keywords);

        // 查询出来的数据放入到 es 中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(2));
        for (int i = 0; i < contents.size(); i++) {

            bulkRequest.add(new IndexRequest(ES_INDEX)
                    .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));

        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulkResponse.hasFailures();
    }


    /**
     * 2 获取这些数据  实现搜索功能
     *
     * @param keywords
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     */
    public List<Map<String, Object>> searchPage(String keywords, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }

        // 条件搜索
        SearchRequest searchRequest = new SearchRequest(ES_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQuery = QueryBuilders.termQuery("title", keywords);
        searchSourceBuilder.query(termQuery);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(60));

        // 执行搜索
        searchRequest.source(searchSourceBuilder);

        // 通过客户端查询
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }


    /**
     * 3 实现搜索功能高亮
     *
     * @param keywords
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     */
    public List<Map<String, Object>> searchPageHighlighter(String keywords, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }

        // 条件搜索
        SearchRequest searchRequest = new SearchRequest(ES_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQuery = QueryBuilders.termQuery("title", keywords);
        searchSourceBuilder.query(termQuery);
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(60));

        //生成高亮查询器
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //高亮查询字段
        highlightBuilder.field("title");
        //如果要多个字段高亮,这项要为false
        highlightBuilder.requireFieldMatch(false);
        //高亮设置
        highlightBuilder.preTags("<span style = 'color:red'>");
        highlightBuilder.postTags("</span>");
        //下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等
        //最大高亮分片数
        highlightBuilder.fragmentSize(800000);
        //从第一个分片获取高亮片段
        highlightBuilder.numOfFragments(0);
        searchSourceBuilder.highlighter(highlightBuilder);


        // 执行搜索
        searchRequest.source(searchSourceBuilder);

        // 通过客户端查询
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            // 解析高亮字段
            Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();  // 原来的结果
            // 解析高亮字段 将原来的字段换为高亮字段
            // 千万记得要记得判断是不是为空, 不然你匹配的第一个结果没有高亮内容, 那么就会报空指针异常, 这个错误一开始真的搞了很久
            if (title != null) {
                Text[] fragments = title.fragments();
                String newTitle = "";
                for (Text fragment : fragments) {
                    newTitle += fragment;
                }
                sourceAsMap.put("title", newTitle);
                System.out.println(newTitle);
            }
            list.add(sourceAsMap);
        }
        return list;
    }

}
