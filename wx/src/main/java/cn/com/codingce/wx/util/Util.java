package cn.com.codingce.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 2460798168@qq.com
 * @date 2019/12/6 16:41
 */
public class Util {


    /**
     * 获取token
     * @param url
     * @return
     */
    public static String get(String url) {
        try {
            URL urlObj = new URL(url);
            //开链接
            URLConnection connection = urlObj.openConnection();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
