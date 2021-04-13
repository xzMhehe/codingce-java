package cn.com.codingce.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mxz
 * @Description:
 * @email codingce@gmail.com
 * @date 2021-01-30 09:11:55
 */
@Configuration
public class GulimallElasticSearchConfig {

//    // @Bean
//    // public RestHighLevelClient esRestClient(){
//    //     RestHighLevelClient client = new RestHighLevelClient(
//    //             RestClient.builder(new HttpHost("192.168.137.14", 9200, "http")));
//    //     return  client;
//    // }
//

    // RequestOptions
    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        // builder.addHeader("Authorization", "Bearer " + TOKEN);
        // builder.setHttpAsyncResponseConsumerFactory(
        //         new HttpAsyncResponseConsumerFactory
        //                 .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient esRestClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("119.23.69.213", 9200, "http")));
        return client;
    }

}
