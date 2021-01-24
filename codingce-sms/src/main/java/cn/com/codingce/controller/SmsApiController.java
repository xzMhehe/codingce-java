package cn.com.codingce.controller;

import cn.com.codingce.service.SendSms;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@CrossOrigin    // 跨域支持
public class SmsApiController {

    @Autowired
    private SendSms sendSms;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("{/send/{phone}}")
    public String code(@PathVariable("phone") String phone) {
        // 调用发送方法 (模拟真实业务 redis)
        String code = redisTemplate.opsForValue().get(phone);
        if (StringUtil.isNullOrEmpty(code)) {
            return phone + ":" + code + "已存在还没有过期";
        }

        // 生成验证码并存储到 redis 中
        code = UUID.randomUUID().toString().substring(0, 4);
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", code);

        boolean isSend = sendSms.send(phone, "templateCode", param);

        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.SECONDS);
            return phone + ":" + code + "发送成功";
        }
        return "发送失败";


    }

}
