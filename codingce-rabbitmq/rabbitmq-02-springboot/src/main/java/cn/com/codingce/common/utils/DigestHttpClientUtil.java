package cn.com.codingce.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ma
 */
@Component
@Slf4j
public class DigestHttpClientUtil {

    public String sendJsonPost(String url, String userName, String password, Map<String, Object> body) {
        return sendJsonPost(url, JSONUtil.toJsonStr(body), DigestHttpClientUtil.digestHttpClient(userName, password, null, null));
    }

    private String sendJsonPost(String url, String body, HttpClient client) {
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
            httpPost.setEntity(new StringEntity(body, "utf-8"));
            log.info("======post==={}=====>{}", url, body);
            HttpResponse response = client.execute(httpPost);
            String responseStr = EntityUtils.toString(response.getEntity(), "utf-8");
            log.info("======post========>{}", responseStr.length() <= 1030 ? responseStr : responseStr.substring(0, 1024));
            return responseStr;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public static HttpClient digestHttpClient(String username, String password, String host, Integer port) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(StrUtil.isBlank(host) ? AuthScope.ANY_HOST : host, port == null ? AuthScope.ANY_PORT : port),
                new UsernamePasswordCredentials(username, password));
        return HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
    }

    public static HttpClient httpClient() {
        return HttpClients.custom().build();
    }

}