package cn.com.codingce.common.utils;

import cn.com.codingce.common.lang.ResultCodeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author ma
 */
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public R setData(Object data) {
        put("data", data);
        return this;
    }

    /**
     * 利用fastjson进行反序列化
     *
     * @param typeReference typeReference
     * @param <T>           T
     * @return t
     */
    public <T> T getData(TypeReference<T> typeReference) {
        // 默认是 map
        Object data = get("data");
        String jsonString = JSON.toJSONString(data);
        T t = JSON.parseObject(jsonString, typeReference);
        return t;
    }

    /**
     * 利用fastjson进行反序列化
     *
     * @param key           key
     * @param typeReference typeReference
     * @param <T>           T
     * @return t
     */
    public <T> T getData(String key, TypeReference<T> typeReference) {
        // 默认是 map
        Object data = get(key);
        String jsonString = JSON.toJSONString(data);
        T t = JSON.parseObject(jsonString, typeReference);
        return t;
    }

    public R() {
        put("code", 0);
        put("msg", "成功");
        put("mcode", ResultCodeEnum.CALL_SUCCESS.Fmt());
    }

    /**
     * 自定义状态码、消息
     *
     * @param code code
     * @param msg  msg
     * @return r
     */
    public static R ok(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "抱歉，服务器出错啦");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {
        return (Integer) this.get("code");
    }

}
