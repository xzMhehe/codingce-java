package cn.com.codingce;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class CodingceSmsApplicationTests {

    @Test
    void testSendSms() {
        // 连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        // 构建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");  // 不要动
        request.setSysVersion("2017-05-25"); // 不要动

        request.setSysAction("SendSms");

        // 自定义参数(手机号, 验证码, 签名, 模板)
        request.putQueryParameter("PhoneNumbers", "手机号");
        request.putQueryParameter("SignName", "全栈自学社区科技");
        request.putQueryParameter("TemplateCode", "SMS_210072888");

        // 构建一个短信验证码
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 2233);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
