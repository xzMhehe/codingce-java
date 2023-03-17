package cn.com.codingce.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class ResT extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public ResT setData(Object data) {
        put("data", data);
        return this;
    }

    // 利用fastjson进行反序列化
    public <T> T getData(TypeReference<T> typeReference) {
        // 默认是map
        Object data = get("data");
        String jsonString = JSON.toJSONString(data);
        T t = JSON.parseObject(jsonString, typeReference);
        return t;
    }

    /**
     * 利用fastjson进行反序列化
     *
     * @param key
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T getData(String key, TypeReference<T> typeReference) {
        Object data = get(key);//默认是map
        String jsonString = JSON.toJSONString(data);
        T t = JSON.parseObject(jsonString, typeReference);
        return t;
    }

    public ResT() {
        put("code", 200);
        put("msg", "success");
    }

    /**
     * 自定义状态码、消息
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResT ok(int code, String msg) {
        ResT r = new ResT();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResT error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static ResT error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static ResT error(int code, String msg) {
        ResT r = new ResT();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResT ok(String msg) {
        ResT r = new ResT();
        r.put("msg", msg);
        return r;
    }

    public static ResT ok(Map<String, Object> map) {
        ResT r = new ResT();
        r.putAll(map);
        return r;
    }

    public static ResT ok() {
        return new ResT();
    }

    @Override
    public ResT put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {

        return (Integer) this.get("code");
    }

}
