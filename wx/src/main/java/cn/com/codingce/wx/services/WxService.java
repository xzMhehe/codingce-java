package cn.com.codingce.wx.services;

import cn.com.codingce.wx.entity.AccessToken;
import cn.com.codingce.wx.util.Util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/6 14:21
 */
@Service
public class WxService {

    private static final String APPKEY = "wx3da4b30dadc22ff7";
    private static final String GET_TOKENURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String TOKEN = "demo";
    private static final String APPID = "wx3da4b30dadc22ff7";
    private static final String APPSECRET = "2e132b85894583981d8410043443b766";
    /**
     * 用于存储token
     */
    private static  AccessToken at;

    /**
     * 验证签名
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public boolean check(String timestamp, String nonce, String signature) {

        String[] strs = new String[]{TOKEN, timestamp, nonce};
        //1）将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(strs);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String strFrom = strs[0] + strs[1] + strs[2];
        String mysig = sha1(strFrom);
        System.out.println("mysig加密" + mysig);
        System.out.println(signature);
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return mysig.equals(signature);
    }

    /**
     * @param strFrom
     * @return
     */
    private String sha1(String strFrom) {
        try {
            //获取加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(strFrom.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder();
            //处理加密结果
            for (byte b:digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取Token
     */
    public static void getToken() {
        String url = GET_TOKENURL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        String tokenStr = Util.get(url);
        System.out.println(tokenStr);
        try {
            JSONObject jsonObject = new JSONObject(tokenStr);
            String token = jsonObject.getString("access_token");
            System.out.println(token);
            String expireIn = String.valueOf(jsonObject.getInt("expires_in"));
            //创建token对象并存起来
            at = new AccessToken(token, expireIn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向外暴露获取token方法
     * @return
     */
    private static String getAccessToken() {
        if (at == null || at.isExpired()) {
            getToken();
        }
        return at.getAccessToken();
    }

    /**
     * 获取用户信息(已关注)
     * @param openid
     * @return
     */
    public static String getUserInfo(String openid) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", getAccessToken()).replace("OPENID", openid);
        System.out.println(url);
        return Util.get(url);
    }

    /**
     * 解析xml数据包    Error on line 1 of document  : 文件提前结束。
     * @param is
     * @return
     */
    public Map<String, String> parseRequest(ServletInputStream is) {
        Map<String, String> map = new HashMap<>();
        try {
            //读取输入流获取文档
            Document document = new SAXReader().read(is);
            //根据文档对象获取根节点
            Element root = document.getRootElement();
            System.out.println(root + "================================");
            //读取根节点所有的子节点
            List<Element> elements = root.elements();
            for (Element e:elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

}
