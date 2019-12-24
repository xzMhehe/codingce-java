package cn.com.codingce.wx;

import cn.com.codingce.wx.services.WxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WxApplicationTests {

    @Autowired
    private WxService service;

    @Test
    void contextLoads() {
    }

    @Test
    void testToken() {
        WxService.getToken();
    }

    @Test
    void testGetUserInfo() {
        String openId = "onuFewA4wjZ9uX1KgiO4ekDxEMK8";
        String userInfo = WxService.getUserInfo(openId);
        System.out.println(userInfo);
    }
}
