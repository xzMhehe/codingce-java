package cn.com.codingce;

import cn.com.codingce.utils.HtmlParseUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodingceEsJdApplicationTests {

    @Autowired
    private HtmlParseUtil htmlParseUtil;

    @Test
    void contextLoads() throws Exception {
        htmlParseUtil.parseJD("java").forEach(System.out::println);
    }

}
