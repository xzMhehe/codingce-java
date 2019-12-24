package cn.com.codingce.wx.controller;

import cn.com.codingce.wx.entity.User;
import cn.com.codingce.wx.services.WxService;
import cn.com.codingce.wx.util.Util;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
@Controller
public class WebController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxService service;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @GetMapping
    public void get() {
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
                logger.error("服务器内部错误", e);
                e.printStackTrace();
            }
        } else {
            System.out.println("Fail");
        }
    }

    /**
     * 接受用户发送消息的接口
     */
    @PostMapping
    public void post() {
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
            logger.error("接受失败", e);
            e.printStackTrace();
        }
    }

    /**
     * 获取授权的用户信息
     * @return
     */
    @GetMapping("/usr")
    public String getUsr() {
        try {
            String code = request.getParameter("code");
            //获取accesstoken的地址
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            url = url.replace("APPID", "wx3da4b30dadc22ff7").replace("SECRET", "2e132b85894583981d8410043443b766").replace("CODE", code);
            String result = Util.get(url);
            //获取result里面的token
            JSONObject jsonObject = new JSONObject(result);
            String at = jsonObject.getString("access_token");
            String openId = jsonObject.getString("openid");
            //拉取用户基本信息
            url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
            url = url.replace("ACCESS_TOKEN", at).replace("OPENID", openId);
            result = Util.get(url);
            JSONObject usr = new JSONObject(result);
            User user = new User(usr.getString("openid"), usr.getString("nickname"), Integer.parseInt(usr.getString("sex")), usr.getString("language"), usr.getString("city"), usr.getString("headimgurl"), usr.getString("privilege"));
            request.setAttribute("user", user);
            return "page/user";
        } catch (JSONException e) {
            e.printStackTrace();
            logger.error("获取用户信息失败", e);
            return null;
        }
    }
}
