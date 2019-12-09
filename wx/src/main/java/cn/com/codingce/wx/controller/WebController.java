package cn.com.codingce.wx.controller;

import cn.com.codingce.wx.services.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/6 12:46
 */
@RestController
public class WebController {

    @Autowired
    private WxService service;

    @GetMapping
    public void get(HttpServletRequest request, HttpServletResponse response) {
        /**
         * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
         * timestamp	时间戳
         * nonce	随机数
         * echostr	随机字符串
         */
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (service.check(timestamp, nonce, signature)) {
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.print(echostr);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Fail");
        }
    }

    @PostMapping
    public void post(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Post");
        try {
            //获取所发送的信息
            ServletInputStream is = request.getInputStream();
            byte[] b = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = is.read(b))!=-1) {
                sb.append(new String(b, 0, len));
            }
            System.out.println(sb.toString());
            Map<String, String> requestMap = service.parseRequest(is);
            System.out.println(requestMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/usr")
    public String getUsr() {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        return "index.html";
    }

}
