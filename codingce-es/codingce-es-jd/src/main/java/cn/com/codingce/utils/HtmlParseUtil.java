package cn.com.codingce.utils;

import cn.com.codingce.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mxz
 */
@Component
public class HtmlParseUtil {


    public List<Content> parseJD(String keywords) throws Exception {

        // 获取请求 https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keywords;

        // 解析网页 (返回 Document 就是浏览器 Document 对象)
        Document document = Jsoup.parse(new URL(url), 30000);

        // 所有在js中可以使用的方法, 这里都可以使用
        Element element = document.getElementById("J_goodsList");
//        System.out.println(element.html());


        ArrayList<Content> goodList = new ArrayList<>();

        // 获取所有的li元素
        Elements elements = element.getElementsByTag("li");
        // 获取元素中的内容
        for (Element el : elements) {
            // 关于这种图片特别多的网站, 所有的图片都是延迟加载的
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            Content content = new Content(title, img, price);
            goodList.add(content);
        }

        return goodList;

    }

    public static void main(String[] args) throws IOException {

        // 获取请求 https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=java";

        // 解析网页 (返回 Document 就是浏览器 Document 对象)
        Document document = Jsoup.parse(new URL(url), 30000);

        // 所有在js中可以使用的方法, 这里都可以使用
        Element element = document.getElementById("J_goodsList");
//        System.out.println(element.html());

        // 获取所有的li元素
        Elements elements = element.getElementsByTag("li");
        // 获取元素中的内容
        for (Element el : elements) {
            // 关于这种图片特别多的网站, 所有的图片都是延迟加载的   source-data-Lazy-img
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            System.out.println("=====================================");
            System.out.println(img);
            System.out.println(price);
            System.out.println(title);
        }
    }


}
