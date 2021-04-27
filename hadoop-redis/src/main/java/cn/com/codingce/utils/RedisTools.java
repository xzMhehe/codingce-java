package cn.com.codingce.utils;

import redis.clients.jedis.Jedis;

import java.util.Map;

//使用原生:jedis完成
public class RedisTools {
    private static Jedis jedis = null;

    //连接数据库redis
    public static void createRedisObj(){
        if (jedis==null) {
            jedis = new Jedis("139.9.34.48",6379);
            //密码
            jedis.auth("mxz123456");
        }
    }

    //注册
    public static synchronized boolean registerUser(String username,String password){
        //连接
        createRedisObj();
        //判断用户
        String pwd = jedis.hget("userTable", username);
        if (pwd!=null) {
            return false;
        }
        //注册
        jedis.hset("userTable",username,password);
        return true;
    }

    //登录
    public static synchronized boolean login(String username,String password){
        createRedisObj();

        String pwd = jedis.hget("userTable", username);
        if (pwd!=null && pwd.equals(password)) {
            return true;
        }
        return false;
    }

    //上传文件的描述信息添加
    public static synchronized boolean uploadFileDesc(String tableName,String filename,String json){
        createRedisObj();

        String file = jedis.hget(tableName, filename);

        if (file!=null) {
            return false;
        }
        jedis.hset(tableName,filename,json);
        return true;
    }
    //显示
    public static Map<String,String> listFiles(String tableName){
        createRedisObj();

        Map<String, String> all = jedis.hgetAll(tableName);

        return all;
    }
    //删除
    public static boolean deleteFile(String tableName,String filename){
        createRedisObj();

        jedis.hdel(tableName,filename);

        return true;
    }
}















































