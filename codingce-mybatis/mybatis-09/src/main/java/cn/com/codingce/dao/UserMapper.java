package cn.com.codingce.dao;

import cn.com.codingce.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> getUsers();

    //根据id查询用户
    User queryUserById(@Param("id") int id);

    int updateUser(User user);
}
