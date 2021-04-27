package cn.com.codingce.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

//redis工具类
@Service
public class RedisUtils {
    @Autowired
    private StringRedisTemplate template;

    //用户注册
    public boolean registerUser(String username,String password){
        //判断用户是否存在
        if (template.opsForHash().get("user",username)==null) {
            //添加用户
            template.opsForHash().put("user",username,password);
            return true;
        }
        return false;
    }

    //用户登录
    public boolean login(String username,String password){
        //判断
        if (template.opsForHash().get("user",username)!=null &&
                template.opsForHash().get("user",username).equals(password)) {
            return true;
        }
        return false;
    }

    //文件上传的描述信息的添加
    public void uploadFileDesc(String tableName,String filename,String json){
        //判断，如果文件不存在，那么，就完成描述信息的添加
        if (template.opsForHash().get(tableName,filename)==null) {
            template.opsForHash().put(tableName,filename,json);
        }
    }

    //显示1
    public List<Object> findAll(String tableName){
        //返回json的方式
        return template.opsForHash().values(tableName);
    }

    //显示2
    public Set findAllKeys(String tableName){
        return template.opsForHash().entries(tableName).entrySet();
    }

    //删除描述信息
    public void deleteFileDesc(String tableName,String filename){
        template.opsForHash().delete(tableName,filename);
    }
}
































